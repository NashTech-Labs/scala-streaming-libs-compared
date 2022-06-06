# scala-streaming-libs-compared
Scala Streaming Libraries Compared


|                                                | FS2 | ZIO Streams | Akka Streams                                                                                                          |
|------------------------------------------------|-----|-------------|-----------------------------------------------------------------------------------------------------------------------|
| Hello World: Finite Stream                     |     |             | [FiniteStreamQuickstart.scala][AkkaFiniteStreamQuickstart] and [FiniteStreamTest.scala][AkkaFiniteStreamTest]         |
| Hello World: Infinite Stream                   |     |             | [InfiniteStreamQuickstart.scala][AkkaInfiniteStreamQuickstart] and [InfiniteStreamTest.scala][AkkaInfiniteStreamTest] |
| Consuming from Kafka (at-least-once semantics) |     |             | [KafkaConsumerQuickstart.scala][AkkaKafkaConsumerQuickstart]                                                          |
| Publishing to Kafka                            |     |             |                                                                                                                       |
| Modularity                                     |     |             |                                                                                                                       |                 
| Keeping State                                  |     |             |                                                                                                                       |
| Fan In                                         |     |             |                                                                                                                       |
| Fan Out                                        |     |             |                                                                                                                       |
| Shutting Down (from Outside)                   |     |             |                                                                                                                       |

[AkkaInfiniteStreamQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/main/scala/com/example/InfiniteStreamQuickstart.scala 
[AkkaFiniteStreamQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/main/scala/com/example/FiniteStreamQuickstart.scala
[AkkaFiniteStreamTest]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/test/scala/com/example/FiniteStreamTest.scala
[AkkaInfiniteStreamTest]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/test/scala/com/example/InfiniteStreamTest.scala
[AkkaKafkaConsumerQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/main/scala/com/example/KafkaConsumerQuickstart.scala  