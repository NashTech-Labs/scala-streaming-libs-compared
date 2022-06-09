# scala-streaming-libs-compared

Scala Streaming Libraries Compared

|                                                | FS2                                                | ZIO Streams | Akka Streams                                                                                                          |
|------------------------------------------------|----------------------------------------------------|-------------|-----------------------------------------------------------------------------------------------------------------------|
| Hello World: Finite Stream                     |                                                    |             | [FiniteStreamQuickstart.scala][AkkaFiniteStreamQuickstart] and [FiniteStreamTest.scala][AkkaFiniteStreamTest]         |
| Hello World: Infinite Stream                   |                                                    |             | [InfiniteStreamQuickstart.scala][AkkaInfiniteStreamQuickstart] and [InfiniteStreamTest.scala][AkkaInfiniteStreamTest] |
| Consuming from Kafka (at-least-once semantics) |                                                    |             | [KafkaConsumerQuickstart.scala][AkkaKafkaConsumerQuickstart]                                                          |
| Publishing to Kafka                            |                                                    |             |                                                                                                                       |
| Modularity                                     |                                                    |             | [FlowQuickstart.scala][AkkaFlowQuickstart] and [FlowTest.scala][AkkaFlowTest]                                         |
| Stateful Function                              |                                                    |             | [StatefulQuickstart.scala][AkkaStatefulQuickstart] and [StatefulFlowTest.scala][AkkaStatefulFlowTest]                 |
| Fan In                                         |                                                    |             | [MergeHubQuickstart.scala][AkkaMergeHubQuickstart]                                                                    |
| Fan Out                                        |                                                    |             | [BroadcastHubQuickstart.scala][AkkaBroadcastHubQuickstart]                                                            |
| Shutting Down (from Outside)                   | [CancelableStreamQuickstart.scala][FS2KillSwitch]  |             |                                                                                                                       |

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