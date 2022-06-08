package com.example

import akka.actor.ActorSystem
import akka.stream.scaladsl.Keep
import akka.stream.testkit.scaladsl.{TestSink, TestSource}
import akka.testkit.TestKit
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.must.Matchers

class FlowTest
    extends TestKit(ActorSystem("FlowTest"))
    with AnyFunSuiteLike
    with Matchers
    with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  test("We can test the flow") {

    val (pub, sub) = TestSource
      .probe[Int]
      .via(SimpleFlow.flow)
      .toMat(TestSink[String]())(Keep.both)
      .run()

    pub.sendNext(42)
    sub.request(1)
    sub.expectNext("42")

  }

}
