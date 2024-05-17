package ru.naumen.scala_course.JobAggregator.utils

import java.nio.charset.StandardCharsets
import java.util.Base64

trait UuidGenerator {

  def randomUuid: String = java.util.UUID.randomUUID.toString.replaceAll("-", "")
}

object UuidGenerator extends UuidGenerator

object TokenGenerator extends UuidGenerator
{
  def generateToken: String = {
    val arr = Base64.getEncoder.encode(randomUuid.getBytes)
    val resStr = new String(arr, StandardCharsets.UTF_8).replaceAll("=", "")
    resStr
  }
}
