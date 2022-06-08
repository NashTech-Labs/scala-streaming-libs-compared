package com.example

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl.{Flow, Keep}
import akka.stream.testkit.scaladsl.{TestSink, TestSource}
import akka.testkit.TestKit
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.must.Matchers

class StatefulFlowTest
    extends TestKit(ActorSystem("FiniteStreamTest"))
    with AnyFunSuiteLike
    with Matchers
    with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  test("We can test the stateful flow") {

    val flowUnderTest: Flow[Int, Int, NotUsed] = StatefulFlow.flow
    val (pub, sub) = TestSource
      .probe[Int]
      .via(flowUnderTest)
      .toMat(TestSink[Int]())(Keep.both)
      .run()

    sub.request(n = 3)
    (1 to 7 * 3).foreach { _ =>
      pub.sendNext(42)
    }
    pub.sendComplete()
    sub.expectNext(42, 42, 42)
    sub.expectComplete()

  }

}
