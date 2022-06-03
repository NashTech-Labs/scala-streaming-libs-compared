package com.example

import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.scaladsl._

object FiniteStreamQuickstart extends App {

  implicit val system: ActorSystem = ActorSystem("QuickStart")
  val logger = Logging(system, "QuickStart")

  Source(1 to 100)
    .map(item => item + 42)
    .wireTap(item => logger.info(s"Got ${item}!"))
    .to(Sink.ignore)
    .run()

}
