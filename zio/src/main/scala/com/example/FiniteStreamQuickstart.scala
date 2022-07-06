package com.example
import zio._
import zio.stream.ZStream

object FiniteStreamQuickstart extends ZIOAppDefault {

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    ZStream
      .range(1, 100)
      .map { item =>
        item + 42
      }
      .tap(item => ZIO.logInfo(s"Got ${item}!"))
      .runDrain
  }

}
