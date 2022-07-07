package com.example

import cats.effect.{ExitCode, IO, IOApp}

object FiniteStreamQuickstart extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    for {
      _ <- fs2.Stream
        .range(1, 101)
        .evalTap(item => IO.println(s"Got ${item}!"))
        .compile
        .drain
    } yield ExitCode.Success
  }

}