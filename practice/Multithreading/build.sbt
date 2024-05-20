ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

libraryDependencies += "dev.zio" %% "zio" % "2.1.1"
libraryDependencies += "dev.zio" %% "zio-streams" % "2.1.1"
libraryDependencies += "dev.zio" %% "zio-profiling-jmh" % "0.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "untitled"
  )
