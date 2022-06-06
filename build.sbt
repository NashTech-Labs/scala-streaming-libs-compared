ThisBuild / organization := "com.example"
ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.13.8"

lazy val AkkaVersion = "2.6.19"

lazy val akkaStreams = (project in file("akka-streams"))
  .settings(
    fork := true,
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,
      "ch.qos.logback" % "logback-classic" % "1.2.11",
      "io.github.embeddedkafka" %% "embedded-kafka" % "3.2.0",
      "org.scalatest" %% "scalatest" % "3.2.12" % Test,
      "com.github.fd4s" %% "fs2-kafka" % "2.5.0-M2"
    )
  )

lazy val fs2 = (project in file("fs2"))
  .settings(
    fork := true,
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.11",
      "io.github.embeddedkafka" %% "embedded-kafka" % "3.2.0",
      "org.scalatest" %% "scalatest" % "3.2.12" % Test,
      "com.github.fd4s" %% "fs2-kafka" % "2.5.0-M2"
    )
  )
