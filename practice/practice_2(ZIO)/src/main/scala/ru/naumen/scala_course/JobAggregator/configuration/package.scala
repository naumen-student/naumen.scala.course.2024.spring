package ru.naumen.scala_course.JobAggregator

import zio._
import zio.config.ReadError
import zio.config.typesafe.TypesafeConfig

package object configuration {

  case class Config(
                    api: ApiConfig,
                    db: DbConfig,
                    liquibase: LiquibaseConfig
                   )

  case class LiquibaseConfig(changeLog: String)
  case class ApiConfig(host: String, port: Int, baseApiUri: String)
  case class DbConfig(driver: String, url: String, user: String, password: String)

  import zio.config.magnolia.DeriveConfigDescriptor.descriptor

  val configDescriptor = descriptor[Config]

  type Configuration = zio.Has[Config]
  object Configuration{
    val live: Layer[ReadError[String], Configuration] = TypesafeConfig.fromDefaultLoader(configDescriptor)
  }
}
