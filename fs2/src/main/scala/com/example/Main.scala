package com.example

import cats.effect.IOApp
import cats.effect.IO
import cats.effect.ExitCode
import com.example.CancelableStreamQuickstart

object Main extends IOApp {
  def run(args: List[String]) =
    CancelableStreamQuickstart.program.as(ExitCode.Success)
}
