import io.gatling.sbt.GatlingPlugin

name := "rest"

version := "1.0"

scalaVersion := "2.10.4"

val akkaVersion = "2.2.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-actor_2.10" % akkaVersion,
  "com.typesafe.akka" % "akka-slf4j_2.10" % akkaVersion,
  "net.liftweb" % "lift-json_2.10" % "2.5.1"
)

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

scalacOptions ++= Seq("-Xmax-classfile-name", "100")
