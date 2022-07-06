# scala-streaming-libs-compared

Scala Streaming Libraries Compared

|                                                | FS2                                                | ZIO Streams                                                   | Akka Streams                                                                                                          |
|------------------------------------------------|----------------------------------------------------|---------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| Hello World: Finite Stream                     |                                                    | [FiniteStreamQuickstart.scala][ZIOFiniteStreamQuickstart]     | [FiniteStreamQuickstart.scala][AkkaFiniteStreamQuickstart] and [FiniteStreamTest.scala][AkkaFiniteStreamTest]         |
| Hello World: Infinite Stream                   |                                                    | [InfiniteStreamQuickstart.scala][ZIOInfiniteStreamQuickstart] | [InfiniteStreamQuickstart.scala][AkkaInfiniteStreamQuickstart] and [InfiniteStreamTest.scala][AkkaInfiniteStreamTest] |
| Consuming from Kafka (at-least-once semantics) |                                                    |                                                               | [KafkaConsumerQuickstart.scala][AkkaKafkaConsumerQuickstart]                                                          |
| Publishing to Kafka                            |                                                    |                                                               |                                                                                                                       |
| Modularity                                     |                                                    |                                                               | [FlowQuickstart.scala][AkkaFlowQuickstart] and [FlowTest.scala][AkkaFlowTest]                                         |
| Stateful Function                              |                                                    |                                                               | [StatefulQuickstart.scala][AkkaStatefulQuickstart] and [StatefulFlowTest.scala][AkkaStatefulFlowTest]                 |
| Fan In                                         |                                                    |                                                               | [MergeHubQuickstart.scala][AkkaMergeHubQuickstart]                                                                    |
| Fan Out                                        |                                                    |                                                               | [BroadcastHubQuickstart.scala][AkkaBroadcastHubQuickstart]                                                            |
| Shutting Down (from Outside)                   | [CancelableStreamQuickstart.scala][FS2KillSwitch]  |                                                               |                                                                                                                       |

[AkkaInfiniteStreamQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/main/scala/com/example/InfiniteStreamQuickstart.scala

[AkkaFiniteStreamQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/main/scala/com/example/FiniteStreamQuickstart.scala

[AkkaFiniteStreamTest]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/test/scala/com/example/FiniteStreamTest.scala

[AkkaInfiniteStreamTest]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/test/scala/com/example/InfiniteStreamTest.scala

[AkkaKafkaConsumerQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/main/scala/com/example/KafkaConsumerQuickstart.scala

[AkkaStatefulQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/main/scala/com/example/StatefulQuickstart.scala

[AkkaStatefulFlowTest]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/test/scala/com/example/StatefulFlowTest.scala

[AkkaFlowQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/main/scala/com/example/FlowQuickstart.scala

[AkkaFlowTest]:  https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/test/scala/com/example/StatefulFlowTest.scala

[AkkaMergeHubQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/main/scala/com/example/FlowQuickstart.scala

[AkkaBroadcastHubQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/main/scala/com/example/FlowQuickstart.scala

[FS2KillSwitch]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/fs2/src/main/scala/com/example/CancelableStreamQuickstart.scala

[ZIOFiniteStreamQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/zio/src/main/scala/com/example/FiniteStreamQuickstart.scala

[ZIOInfiniteStreamQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/zio/src/main/scala/com/example/InfiniteStreamQuickstart.scala

Introduction
============

The purpose of this comparison is to help Scala engineers who are in the process of switching from one streaming
library to another one (e.g., you changed jobs and the company you joined uses ZIO Streams, but you're only familiar
with
Akka Streams and have to learn ZIO Streams fast). Currently, 3 streaming libraries are competing for mind share in the
Scala ecosystem: FS2, Akka Streams
and ZIO Streams. Each library has advantages and disadvantage, the people behind all these libraries are extremely
talented and brilliant. This repo does not attempt to benchmark the libraries against each other, in any way.

Some Thoughts
=============

TODO

Contributions
=============

We not only welcome but actively seek community contributions. We seek to create an inclusive environment,
and we follow the [Scala Code of Conduct](https://www.scala-lang.org/conduct/).

Credits
=======

Rohan Sircar (@rohan-sircar), Eldar Albossyn (@EldarKZ16) and Sebastian Harko (@sebastianharko) have contributed to this
repo, and we
are grateful to our employer [Knoldus](https://wwww.knoldus.com/) for giving us the opportunity to spend some time on
this. Knoldus, a consulting company, has been active in the Scala space for well over a decade and is an affiliate
member
of the [Scala Center](https://scala.epfl.ch/) and has helped customers big and small adopt Scala and its ecosystem. 




