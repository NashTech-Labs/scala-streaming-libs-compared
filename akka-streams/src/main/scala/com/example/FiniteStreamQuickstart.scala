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

object FiniteStreamQuickstart extends App {

  implicit val system: ActorSystem = ActorSystem("QuickStart")
  val logger = Logging(system, "QuickStart")

  Source(1 to 100)
    .map(item => item + 42)
    .wireTap(item => logger.info(s"Got ${item}!"))
    .to(Sink.ignore)
    .run()

}
