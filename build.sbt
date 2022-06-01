ThisBuild / organization := "com.example"
ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "3.1.2"

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
      "org.scalatest" %% "scalatest" % "3.2.12" % Test
    )
  )
