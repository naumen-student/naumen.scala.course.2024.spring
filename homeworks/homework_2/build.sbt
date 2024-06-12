name := "Lecture2"

version := "0.1"

scalaVersion := "3.3.3"

libraryDependencies += "com.lihaoyi" %% "utest" % "0.5.3" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")