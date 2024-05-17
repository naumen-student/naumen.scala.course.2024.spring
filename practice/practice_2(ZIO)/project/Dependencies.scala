import sbt._

object Dependencies {
  object Versions {

    lazy val KindProjectorVersion = "0.10.3"

    lazy val LiquibaseVersion = "3.4.2"

    lazy val PostgresVersion = "42.2.8"

    lazy val LogbackVersion = "1.2.3"

    lazy val Specs2Version = "4.9.3"

    lazy val CirceVersion = "0.13.0"

    lazy val Http4sVersion = "0.21.7"

    lazy val Fs2Version = "2.2.1"

    lazy val CommonsIoVersion = "2.4"

    lazy val DoobieVersion = "0.8.8"

    lazy val scalaTagsVersion = "0.8.3"


    lazy val ZioVersion = "1.0.4"
    lazy val TapirVersion = "0.17.8"
    lazy val XStreamVersion = "1.4.16"
    lazy val JbCryptVersion = "0.3m"
  }

  import Versions._

  lazy val kindProjector = "org.typelevel" %% "kind-projector" % KindProjectorVersion

  lazy val liquibase = "org.liquibase" % "liquibase-core" % LiquibaseVersion

  lazy val postgres = "org.postgresql" % "postgresql" % PostgresVersion

  lazy val apacheCommons = "org.apache.commons" % "commons-lang3" % "3.11"
  lazy val commonsIO = "commons-io" % "commons-io" % CommonsIoVersion


  lazy val circe: Seq[ModuleID] = Seq(
    "io.circe" %% "circe-generic" % CirceVersion,
    "io.circe" %% "circe-generic-extras"% CirceVersion,
    "io.circe" %% "circe-parser" % CirceVersion
  )

  lazy val http4sServer: Seq[ModuleID] = Seq(
    "org.http4s" %% "http4s-dsl"          % Http4sVersion,
    "org.http4s" %% "http4s-circe"        % Http4sVersion,
    "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
    "org.http4s" %% "http4s-blaze-client" % Http4sVersion
  )

  lazy val doobie: Seq[ModuleID] = Seq(
    "org.tpolecat" %% "doobie-core"     % DoobieVersion,
    "org.tpolecat" %% "doobie-postgres" % DoobieVersion,
    "org.tpolecat" %% "doobie-specs2"   % DoobieVersion,
    "org.tpolecat" %% "doobie-hikari"    % DoobieVersion,
    "org.tpolecat" %% "doobie-quill"    % DoobieVersion
  )

  lazy val zio: Seq[ModuleID] = Seq(
    "dev.zio" %% "zio" % ZioVersion,
    "dev.zio" %% "zio-streams" % "1.0.2",
    "dev.zio" %% "zio-logging-slf4j" % "0.5.6",
    "dev.zio" %% "zio-macros" % ZioVersion
  )

  lazy val tapir: Seq[ModuleID] = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-zio" % "0.17.8",
    "com.softwaremill.sttp.tapir" %% "tapir-zio-http4s-server" % TapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-cats"               % TapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe"         % TapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs"       % TapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % TapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-http4s"  % TapirVersion
  )

  lazy val jacksonDatabind = "com.fasterxml.jackson.core" % "jackson-databind" % "2.10.2"

  lazy val logback = "ch.qos.logback"  %  "logback-classic" % LogbackVersion

  lazy val xStream = "com.thoughtworks.xstream" % "xstream" % XStreamVersion

  lazy val jwtCirce = "com.github.jwt-scala" %% "jwt-circe" % "8.0.3"

  lazy val cryptoBits = "org.reactormonk" %% "cryptobits" % "1.3"

  lazy val zioConfig: Seq[ModuleID] = Seq(
    "dev.zio" %% "zio-config" % "1.0.5",
    "dev.zio" %% "zio-config-magnolia" % "1.0.5",
    "dev.zio" %% "zio-config-typesafe" % "1.0.5"
  )

}
