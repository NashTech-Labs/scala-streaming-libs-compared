package com.example

import akka.Done
import akka.actor.{ActorSystem, CoordinatedShutdown}
import akka.event.{Logging, LoggingAdapter}
import akka.kafka.scaladsl.Consumer.DrainingControl
import akka.kafka.scaladsl.{Committer, Consumer}
import akka.kafka.{CommitterSettings, ConsumerSettings, Subscriptions}
import akka.stream.scaladsl.Sink
import akka.util.Timeout
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

object KafkaConsumerQuickstart extends App {

  implicit val system: ActorSystem = ActorSystem("QuickStart")
  import system.dispatcher
  val logger: LoggingAdapter = Logging(system, "QuickStart")

  val embeddedKafkaHelper = EmbeddedKafkaHelper(system)
  embeddedKafkaHelper
    .startKafka() // Starts EmbeddedKafka, creates a topic, publishes some messages etc.

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
    CoordinatedShutdown.PhaseBeforeActorSystemTerminate,
    "shutdown stream"
  ) { () =>
    implicit val timeout: Timeout = 5.seconds
    logger.info("Draining and shutting down the stream!")
    control.drainAndShutdown()
  }

  CoordinatedShutdown(system).addTask(
    CoordinatedShutdown.PhaseActorSystemTerminate,
    "shutdown kafka"
  ) { () =>
    Future {
      embeddedKafkaHelper.stopKafka()
    }.map(_ => Done)
  }

}
