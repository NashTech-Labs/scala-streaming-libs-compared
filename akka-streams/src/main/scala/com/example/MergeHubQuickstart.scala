package com.example

import akka.actor.{ActorSystem, Cancellable}
import akka.event.{Logging, LoggingAdapter}
import akka.stream.scaladsl.{MergeHub, Sink, Source}

import scala.concurrent.duration._

object MergeHubQuickstart extends App {

  implicit val system: ActorSystem = ActorSystem("MergeHubQuickstart")
  val logger: LoggingAdapter = Logging(system, "MergeHubQuickstart")

  val consumer = {
    Sink.foreach[String] { item => logger.info(s"${item}") }
  }

  val toConsumer =
    MergeHub.source[String](perProducerBufferSize = 16).to(consumer).run()

  case object Tick

  def buildProducerStream(index: Int): Source[String, Cancellable] = {
    Source
      .tick(
        initialDelay = scala.util.Random.nextInt(100).milliseconds,
        interval = scala.util.Random.nextInt(250).milliseconds,
        Tick
      )
      .map(_ => s"Stream number #${index} says hello!")
  }

  val stream1 = buildProducerStream(1)
  val stream2 = buildProducerStream(2)

  stream1.runWith(toConsumer)
  stream2.runWith(toConsumer)

}
