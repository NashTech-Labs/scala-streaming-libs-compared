package com.example

import cats.effect.std.Random
import cats.effect.{ExitCode, IO, IOApp}

object InfiniteStreamQuickstart extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    for {
      rnd <- Random.scalaUtilRandom[IO]
      _ <- fs2.Stream
        .repeatEval(rnd.nextIntBounded(10))
        .evalTap(item => IO.println(s"Got ${item}!"))
        .compile
        .drain
    } yield ExitCode.Success
  }
}
