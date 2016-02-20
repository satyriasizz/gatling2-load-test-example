name := "rest"

version := "1.0"

scalaVersion := "2.11.6"

val akkaVersion = "2.3.9"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "net.liftweb" % "lift-json_2.10" % "2.5.1"
)

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

scalacOptions ++= Seq("-Xmax-classfile-name", "100")
