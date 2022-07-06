package com.example

import zio.stream.ZStream
import zio.{Random, Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

object StatefulQuickstart extends ZIOAppDefault {
  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {

    ZStream
      .repeatZIO(Random.nextIntBetween(0, 100))
      .mapAccum(Map.empty[Int, Int]) { (counter, item) =>
        {
          counter.get(item) match {
            case Some(value: Int) if value == 6 =>
              (counter + (item -> 0), Some(item))
            case Some(value: Int) =>
              (counter + (item -> (value + 1)), None)
            case None =>
              (counter + (item -> 1), None)
          }
        }
      }
      .mapConcat {
        case Some(value) => value :: Nil
        case None        => Nil
      }
      .tap(item => ZIO.logInfo(s"Element ${item} has been since 7 times!"))
      .runDrain
  }
}
