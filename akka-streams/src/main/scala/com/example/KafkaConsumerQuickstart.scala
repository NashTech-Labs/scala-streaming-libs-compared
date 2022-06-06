package com.example

import akka.Done
import akka.actor.{ActorSystem, CoordinatedShutdown}
import akka.event.{Logging, LoggingAdapter}
import akka.kafka.{CommitterSettings, ConsumerSettings, Subscriptions}
import akka.kafka.scaladsl.{Committer, Consumer}
import akka.kafka.scaladsl.Consumer.DrainingControl
import akka.stream.scaladsl.Sink
import akka.util.Timeout
import com.example.KafkaConsumerQuickstart.logger
import io.github.embeddedkafka.EmbeddedKafka
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.{
  Deserializer,
  Serializer,
  StringDeserializer,
  StringSerializer
}

import scala.concurrent.duration.DurationInt

object EmbeddedKafkaHelper {

  def startKafka(): Unit = {

    implicit val serializer: Serializer[String] = new StringSerializer()
    implicit val deserializer: Deserializer[String] = new StringDeserializer()

    logger.info("Starting EmbeddedKafka!")
    EmbeddedKafka.start()

    logger.info("Creating Kafka topic!")
    EmbeddedKafka.createCustomTopic(
      "events",
      topicConfig = Map.empty,
      partitions = 7,
      replicationFactor = 1
    )

    (1 to 100).foreach { id =>
      val key = s"User${id}"
      val msg = s"Hello from ${id}"
      logger.info(s"Publishing message to Kafka: ${key} -> ${msg}")
      EmbeddedKafka.publishToKafka[String, String](
        "events",
        key,
        msg
      )
    }
  }
}

object KafkaConsumerQuickstart extends App {

  implicit val system: ActorSystem = ActorSystem("QuickStart")
  import system.dispatcher
  val logger: LoggingAdapter = Logging(system, "QuickStart")

  EmbeddedKafkaHelper.startKafka()

  val config = system.settings.config.getConfig("akka.kafka.consumer")

  val consumerSettings =
    ConsumerSettings(system, new StringDeserializer, new StringDeserializer)
      .withBootstrapServers("127.0.0.1:6001")
      .withGroupId("group1")
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  val committerSettings = CommitterSettings(system)

  val control: DrainingControl[Done] =
    Consumer
      .committableSource(consumerSettings, Subscriptions.topics("events"))
      .map { msg =>
        logger.info(s"Got message: ${msg.record.key} -> ${msg.record.value}")
        msg.committableOffset
      }
      .via(Committer.flow(committerSettings))
      .toMat(Sink.ignore)(DrainingControl.apply)
      .run()

  CoordinatedShutdown(system).addTask(
    CoordinatedShutdown.PhaseActorSystemTerminate,
    "shutdown"
  ) { () =>
    implicit val timeout: Timeout = 5.seconds
    logger.info("Draining and shutting down the stream!")
    control.drainAndShutdown()
  }

  CoordinatedShutdown(system).addJvmShutdownHook {
    logger.info("Shutting down EmbeddedKafka!")
    EmbeddedKafka.stop()
  }

}
