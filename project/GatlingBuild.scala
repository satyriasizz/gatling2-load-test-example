import sbt._
import sbt.Keys._

import io.gatling.sbt.GatlingPlugin._

object GatlingBuild extends Build {

  val libs = Seq(
    "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.0.0-RC5" % "test",
    "io.gatling" % "gatling-bundle" % "2.0.0-RC5" % "test" artifacts (Artifact("gatling-bundle", "zip", "zip", "bundle")),
    "io.gatling" % "test-framework" % "1.0-RC5" % "test"
  )

  val root = Project("scala-rest", file("."))
    .settings(gatlingSettings: _*)
    .settings(resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
    .configs(Gatling)
    .settings(organization := "io.gatling.sbt.test")
    .settings(libraryDependencies ++= libs)
    .settings(scalaSource in Gatling := new File(s"${System.getProperty("user.dir")}/src/test/gatling/scala"))
}
