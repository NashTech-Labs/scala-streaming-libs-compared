package com.example

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl.Source
import akka.stream.testkit.scaladsl.TestSink
import akka.testkit.TestKit
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.must.Matchers

class InfiniteStreamTest
    extends TestKit(ActorSystem("InfiniteStreamTest"))
    with AnyFunSuiteLike
    with Matchers
    with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  test("We can test an infinite stream") {

    val sourceUnderTest: Source[Int, NotUsed] = InfiniteStream.source

    sourceUnderTest
      .runWith(TestSink[Int]())
      .request(1000)
      .expectNextPF {
        case item: Int if item >= 0 && item <= 9 =>
      }
  }

}
