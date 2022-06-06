package com.example

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl.Source
import akka.stream.testkit.scaladsl.TestSink
import akka.testkit.TestKit
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.must.Matchers

class FiniteStreamTest
    extends TestKit(ActorSystem("FiniteStreamTest"))
    with AnyFunSuiteLike
    with Matchers
    with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  test("We can test a finite stream") {

    val sourceUnderTest: Source[Int, NotUsed] = FiniteStream.source

    sourceUnderTest
      .runWith(TestSink[Int]())
      .request(100)
      .expectNext(43, 44, (45 to 142): _*)
      .expectComplete()
  }

}
