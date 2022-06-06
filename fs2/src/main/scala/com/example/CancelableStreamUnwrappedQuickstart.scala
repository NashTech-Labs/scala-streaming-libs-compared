package com.example

import cats.effect.{IO, IOApp}
import fs2.kafka._
import scala.concurrent.duration._
import cats.effect.std.Random
import cats.effect.std.Queue
// import cats.effect.std.
import fs2.concurrent.Topic
import cats.syntax.all._
import scala.concurrent.duration._
import cats.effect.kernel.Deferred
import cats.effect.kernel.Ref

object CancelableStreamUnwrappedQuickstart {
  def cancelableProducer(rnd: Random[IO], cancelSignal: Ref[IO, Int]) =
    fs2.Stream
      .repeatEval(rnd.nextIntBounded(10))
      .evalTap(i => IO.println(s"Generated $i") >> IO.sleep(1.second))
      .evalMap(_ => cancelSignal.get.map(i => if (i === 0) true else false))
      .takeThrough(b => b)

  def canceler(cancelSignal: Ref[IO, Int]) = for {
    _ <- IO.sleep(5.seconds)
    _ <- cancelSignal.set(1)
  } yield ()

  val program = for {
    rnd <- Random.scalaUtilRandom[IO]
    r <- Ref.of[IO, Int](0)
    p = cancelableProducer(rnd, r).compile.drain.void
    c = canceler(r).void
    _ <- IO.parSequenceN(2)(List(c, p))
  } yield ()
}
