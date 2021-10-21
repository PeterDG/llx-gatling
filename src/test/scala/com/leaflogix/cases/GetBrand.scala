package com.leaflogix.cases

import io.gatling.http.Predef._
import io.gatling.core.Predef._
import ru.tinkoff.gatling.config.SimulationConfig.{baseAuthUrl, intensity}

object GetBrand {
  val sessionHeaders = Map("Authorization" -> baseAuthUrl,
    "Content-Type" -> "application/json")

  val getBrand = http("GET /brand")
    .get("/")
    .headers(sessionHeaders)
    .check(status is 200)

}
