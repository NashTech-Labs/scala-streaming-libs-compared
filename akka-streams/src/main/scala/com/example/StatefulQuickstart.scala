package com.example

import akka.NotUsed
import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.scaladsl.{Flow, Sink, Source}

import scala.collection.mutable
import scala.util.Random

object StatefulFlow {

  val flow: Flow[Int, Int, NotUsed] = Flow[Int].statefulMapConcat { () =>
    val counter = mutable.Map.empty[Int, Int]

    { element =>
      counter.get(element) match {
        case Some(value) if value == 6 =>
          counter(element) = 0
          element :: Nil
        case Some(value) =>
          counter(element) = value + 1
          Nil
        case None =>
          counter(element) = 1
          Nil
      }
    }
  }
}

object StatefulQuickstart extends App {

  implicit val system: ActorSystem = ActorSystem("QuickStart")
  val logger = Logging(system, "QuickStart")

  Source
    .cycle(() => Iterator.single(Random.nextInt(100)))
    .via(StatefulFlow.flow)
    .wireTap { item => logger.info(s"Element ${item} has been since 7 times!") }
    .runWith(Sink.ignore)

}
