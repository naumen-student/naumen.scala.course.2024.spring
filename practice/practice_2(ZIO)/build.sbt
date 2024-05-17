ThisBuild / organization := "ru.naumen.scala_course"
ThisBuild / scalaVersion := "2.13.3"

lazy val root = (project in file(".")).
  settings(
    name := "Job aggregator",
    libraryDependencies ++= Dependencies.doobie,
    libraryDependencies ++= Dependencies.http4sServer,
    libraryDependencies ++= Dependencies.circe,
    libraryDependencies ++= Dependencies.zioConfig,
    libraryDependencies ++= Dependencies.zio,
    libraryDependencies ++= Dependencies.tapir,
    libraryDependencies ++= Seq(
      Dependencies.kindProjector,
      Dependencies.liquibase,
      Dependencies.postgres,
      Dependencies.jacksonDatabind,
      Dependencies.logback,
      Dependencies.apacheCommons,
      Dependencies.commonsIO,
      Dependencies.xStream,
      Dependencies.jwtCirce,
      Dependencies.cryptoBits
    ),
    addCompilerPlugin(Dependencies.kindProjector)
  )

  scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings",
  "-Ymacro-annotations"
)

enablePlugins(JavaAppPackaging)
