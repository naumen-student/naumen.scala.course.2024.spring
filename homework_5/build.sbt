version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies += "com.lihaoyi" %% "utest" % "0.5.3" % "test"

libraryDependencies += "org.typelevel" %% "cats-core" % "2.10.0"

testFrameworks += new TestFramework("utest.runner.Framework")
