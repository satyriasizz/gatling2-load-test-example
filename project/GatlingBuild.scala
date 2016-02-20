import sbt._
import sbt.Keys._

import io.gatling.sbt.GatlingPlugin

object GatlingBuild extends Build {

  val libs = Seq(
    "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.5" % "test",
    "io.gatling"            % "gatling-test-framework"    % "2.1.5" % "test"
  )

  val root = Project("scala-rest", file("."))
    .enablePlugins(GatlingPlugin)
    .settings(organization := "io.gatling.sbt.test")
    .settings(libraryDependencies ++= libs)
}
