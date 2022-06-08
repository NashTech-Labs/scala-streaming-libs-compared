package com.example

import akka.NotUsed
import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.scaladsl._

import scala.util.Random

object InfiniteStream {
  val source: Source[Int, NotUsed] = Source
    .cycle(() => Iterator.single(Random.nextInt(10)))
}

object InfiniteStreamQuickstart extends App {

  implicit val system: ActorSystem = ActorSystem("InfiniteStreamQuickstart")
  val logger = Logging(system, "InfiniteStreamQuickstart")

  InfiniteStream.source
    .wireTap(item => logger.info(s"Got ${item}!"))
    .to(Sink.ignore)
    .run()

}
