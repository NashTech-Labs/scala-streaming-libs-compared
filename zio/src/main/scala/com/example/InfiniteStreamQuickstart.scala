package com.example

import zio.stream.ZStream
import zio.{Random, Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

object InfiniteStreamQuickstart extends ZIOAppDefault {

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    ZStream
      .repeatZIO(Random.nextIntBetween(0, 10))
      .tap(item => ZIO.logInfo(s"Got ${item}!"))
      .runDrain
  }

}
