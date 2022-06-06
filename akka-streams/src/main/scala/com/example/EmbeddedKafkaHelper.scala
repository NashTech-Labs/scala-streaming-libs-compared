package com.example

import akka.actor.ActorSystem
import akka.event.Logging
import io.github.embeddedkafka.EmbeddedKafka
import org.apache.kafka.common.serialization.{Deserializer, Serializer, StringDeserializer, StringSerializer}

case class EmbeddedKafkaHelper(system: ActorSystem) {

  val logger = Logging(system, "EmbeddedKafkaHelper")

  def startKafka(): Unit = {

    implicit val serializer: Serializer[String] = new StringSerializer()
    implicit val deserializer: Deserializer[String] = new StringDeserializer()

    logger.info("Starting EmbeddedKafka!")
    EmbeddedKafka.start()

    while (!EmbeddedKafka.isRunning) {}

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

  def stopKafka(): Unit = {
    logger.info("Stopping EmbeddedKafka")
    EmbeddedKafka.stop()
  }

}
