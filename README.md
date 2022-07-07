# scala-streaming-libs-compared

Scala Streaming Libraries Compared

|                                                | FS2                                                | ZIO Streams                                                   | Akka Streams                                                                                                          |
|------------------------------------------------|----------------------------------------------------|---------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| Hello World: Finite Stream                     |                                                    | [FiniteStreamQuickstart.scala][ZIOFiniteStreamQuickstart]     | [FiniteStreamQuickstart.scala][AkkaFiniteStreamQuickstart] and [FiniteStreamTest.scala][AkkaFiniteStreamTest]         |
| Hello World: Infinite Stream                   |                                                    | [InfiniteStreamQuickstart.scala][ZIOInfiniteStreamQuickstart] | [InfiniteStreamQuickstart.scala][AkkaInfiniteStreamQuickstart] and [InfiniteStreamTest.scala][AkkaInfiniteStreamTest] |
| Consuming from Kafka (at-least-once semantics) |                                                    |                                                               | [KafkaConsumerQuickstart.scala][AkkaKafkaConsumerQuickstart]                                                          |
| Publishing to Kafka                            |                                                    |                                                               |                                                                                                                       |
| Modularity                                     |                                                    |                                                               | [FlowQuickstart.scala][AkkaFlowQuickstart] and [FlowTest.scala][AkkaFlowTest]                                         |
| Stateful Function                              |                                                    | [StatefulQuickstart.scala][ZIOStatefulQuickstart]             | [StatefulQuickstart.scala][AkkaStatefulQuickstart] and [StatefulFlowTest.scala][AkkaStatefulFlowTest]                 |
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

[ZIOStatefulQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/zio/src/main/scala/com/example/StatefulQuickstart.scala

Introduction
============


The purpose of this comparison is to help Scala engineers who are switching from one streaming library to another. (For example, you recently changed your job and the company you joined uses ZIO Streams, but you're familiar with Akka Streams and have to learn ZIO Streams fast.)

Currently, three streaming libraries are competing for mind share in the Scala ecosystem: FS2, Akka Streams, and ZIO Streams. Each has its advantages and disadvantages, and the people behind all three are extremely talented and brilliant. This repo does not attempt to benchmark the libraries against each other in any way.

Some Random Thoughts and Observations
=====================================
* The three libraries are similar to each other. They all provide operators such as mapAsync, mapConcat, groupWithin, and throttle. If you're a new Scala developer, try to master one of them and see if you can shift your frame of mind ("thinkin' in streams").

* All three libraries provide "backpressure" in some way. Backpressure is essential when, for example, you are working with a legacy system and are trying to protect a slow legacy service from being overloaded by too fast a producer. However, the way in which backpressure is implemented in ZIO differs a little. ZIO claims to be pull-based and claims to have an implementation where, "when the sink asks for one element, then that ripples all the way back through the very edges of the system" (see: ZIO Streaming Docs).

* Akka Streams has a concept of "materialized value", and FS2 and ZIO Streams do not. Materialized value can be useful (i.e., you can materialize a KillSwitch) but materialized values make the Akka Streams DSL a bit more complicated. There's a bit of a learning curve, and you have to learn how to combine materialized values from multiple operators.

* One of the best places to learn about Akka Streams is Colin Breck's blog. I recommend the article "Akka Streams: A Motivating Example" if you are a confused beginner. For FS2, one great book that gives a good introduction is Functional Event-Driven Architecture, by Gabriel Volpe. It provides not only an introduction to FS2 but also an introduction to event sourcing, CQRS, and technologies such as Kafka and Pulsar.

* For integration with external products, Akka Streams is the richest of the three libraries, since it has Alpakka.

* ZIO Streams itself is not so tough to learn, but to use it effectively you have to learn the dependency injection mechanism (ZIO Environment). It is definitely worth every minute of the time you spend doing so!

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




