name := "Lecture4"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies += "com.lihaoyi" %% "utest" % "0.5.3" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % Test

testFrameworks += new TestFramework("utest.runner.Framework")