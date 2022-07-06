package com.example

import akka.NotUsed
import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.stream.scaladsl._

object SimpleFlow {

  val flow: Flow[Int, String, NotUsed] = Flow[Int].map(_.toString)

}

object FlowQuickstart extends App {

  implicit val system: ActorSystem = ActorSystem("FlowQuickstart")
  val logger: LoggingAdapter = Logging(system, "FlowQuickstart")

  Source(1 to 100)
    .via(SimpleFlow.flow)
    .wireTap(item => logger.info(s"Got ${item}!"))
    .to(Sink.ignore)
    .run()
}
