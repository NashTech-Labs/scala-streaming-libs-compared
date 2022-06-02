package com.example

import akka.stream._
import akka.stream.scaladsl._
import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.util.ByteString
import scala.concurrent._
import scala.concurrent.duration._
import java.nio.file.Paths
import akka.event.Logging
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
