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

final class KillSwitch(cancelSignal: Ref[IO, Boolean]) {
  val kill = cancelSignal.set(true)

  def attach[T](stream: fs2.Stream[IO, T]) = stream
    .evalMap(item => cancelSignal.get.map(f => (item, !f)))
    .takeThrough { case (item, f) => f }
    .map(_._1)

}

object KillSwitch {
  def apply() = for {
    r <- Ref.of[IO, Boolean](false)
  } yield new KillSwitch(r)
}

object CancelableStreamQuickstart {
  def cancelableProducer(rnd: Random[IO], killSwitch: KillSwitch) =
    killSwitch.attach(
      fs2.Stream
        .repeatEval(rnd.nextIntBounded(10))
        .evalTap(i => IO.println(s"Generated $i") >> IO.sleep(1.second))
    )

  def canceler(killSwitch: KillSwitch) = for {
    _ <- IO.sleep(5.seconds)
    _ <- IO.println("Killing stream...")
    _ <- killSwitch.kill
  } yield ()

  val program = for {
    rnd <- Random.scalaUtilRandom[IO]
    k <- KillSwitch()
    p = cancelableProducer(rnd, k).compile.drain.void
    c = canceler(k).void
    _ <- IO.parSequenceN(2)(List(c, p))
  } yield ()
}
