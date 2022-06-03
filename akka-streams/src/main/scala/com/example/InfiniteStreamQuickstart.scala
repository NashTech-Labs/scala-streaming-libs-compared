package com.example

import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.scaladsl._

import scala.util.Random

object InfiniteStreamQuickstart extends App {

  implicit val system: ActorSystem = ActorSystem("QuickStart")
  val logger = Logging(system, "QuickStart")

  Source
    .cycle(() => Iterator.single(Random.nextInt(10)))
    .wireTap(item => logger.info(s"Got ${item}!"))
    .to(Sink.ignore)
    .run()

}
