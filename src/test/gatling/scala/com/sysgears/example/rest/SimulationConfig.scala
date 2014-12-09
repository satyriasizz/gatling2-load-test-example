package com.sysgears.example.rest

import akka.event.slf4j.SLF4JLogging
import com.typesafe.config.ConfigFactory
import scala.util.Try
import com.typesafe.config.ConfigException.Missing

object SimulationConfig extends SLF4JLogging {

  /**
   * Application config object.
   */
  private[this] val config = ConfigFactory.load()

  /**
   * Gets required string from config file or throws an exception if string not found.
   *
   * @param path path to string
   * @return string fetched by path
   */
  private[this] def getRequiredString(path: String): String = {
    Try(config.getString(path)).getOrElse {
      handleError(path)
    }
  }

  /**
   * Gets required string from config file or throws an exception if string not found.
   *
   * @param path path to string
   * @return string fetched by path
   */
  private[this] def getRequiredInt(path: String): Int = {
    Try(config.getInt(path)).getOrElse {
      handleError(path)
    }
  }

  private[this] def handleError(path: String) = {
    val errMsg = s"Missing required configuration entry: $path"
    log.error(errMsg)
    throw new Missing(errMsg)
  }

  /**
   * URL for test.
   */
  val baseURL = getRequiredString("service.host")

  /**
   * Endpoint link.
   */
  val customerLink = getRequiredString("service.api_link")

  /**
   * Scenario repeat count.
   */
  val repeatCount = getRequiredInt("gatling.repeat_count")

  /**
   * Count of users for simulation.
   */
  val threads = getRequiredInt("gatling.repeat_count")
}
