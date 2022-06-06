package com.example

import akka.NotUsed
import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.stream.scaladsl._

object FiniteStream {
  val source: Source[Int, NotUsed] = Source(1 to 100)
    .map(item => item + 42)
}

object FiniteStreamQuickstart extends App {

  implicit val system: ActorSystem = ActorSystem("QuickStart")
  val logger: LoggingAdapter = Logging(system, "QuickStart")

  FiniteStream.source
    .wireTap(item => logger.info(s"Got ${item}!"))
    .to(Sink.ignore)
    .run()

}
