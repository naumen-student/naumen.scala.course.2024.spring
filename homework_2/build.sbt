version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies += "com.lihaoyi" %% "utest" % "0.5.3" % "test"
libraryDependencies += "org.scala-exercises" %% "definitions" % "0.6.7"

testFrameworks += new TestFramework("utest.runner.Framework")