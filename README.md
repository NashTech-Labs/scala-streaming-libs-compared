# scala-streaming-libs-compared
Scala Streaming Libraries Compared


|                              | FS2 | ZIO Streams | Akka Streams                                                   |
|------------------------------|-----|-------------|----------------------------------------------------------------|
| Hello World: Finite Stream   |     |             | [FiniteStreamQuickstart.scala][AkkaFiniteStreamQuickstart]     |
| Hello World: Infinite Stream |     |             | [InfiniteStreamQuickstart.scala][AkkaInfiniteStreamQuickstart] |
| Consuming from Kafka         |     |             |                                                                |
| Publishing to Kafka          |     |             |                                                                |
| Modularity                   |     |             |                                                                |                 
| Keeping State                |     |             |                                                                |
| Fan In                       |     |             |                                                                |
| Fan Out                      |     |             |                                                                |
| Shutting Down (from Outside) |     |             |                                                                |

[AkkaInfiniteStreamQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/main/scala/com/example/InfiniteStreamQuickstart.scala 
[AkkaFiniteStreamQuickstart]: https://gitpod.io/#https://github.com/knoldus/scala-streaming-libs-compared/blob/main/akka-streams/src/main/scala/com/example/FiniteStreamQuickstart.scala