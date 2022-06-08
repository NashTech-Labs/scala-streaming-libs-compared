package com.example

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.stream.scaladsl.{BroadcastHub, Keep, Sink, Source}

import scala.concurrent.duration._

object BroadcastHubQuickstart {

  implicit val system: ActorSystem = ActorSystem("BroadcastHubQuickstart")
  val logger: LoggingAdapter = Logging(system, "BroadcastHubQuickstart")

  case object Tick

  val producer = Source
    .tick(
      initialDelay = 0.second,
      interval = 500.milliseconds,
      tick = Tick
    )
    .toMat(BroadcastHub.sink(bufferSize = 16))(Keep.right)
    .run()

  def buildConsumerStream(index: Int) = {
    producer
      .wireTap { _ =>
        s"Stream #${index} received message from the producer!"
      }
      .to(Sink.ignore)
  }

  val stream1 = buildConsumerStream(1)
  val stream2 = buildConsumerStream(2)

  stream1.run()
  stream2.run()

}
