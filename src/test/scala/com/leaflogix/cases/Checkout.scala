package com.leaflogix.cases

import com.leaflogix.params.DataPaths.{CHECKOUT_REQUEST, CHECKOUT_RESPONSE, PARAMETERS}
import com.leaflogix.params.paths.POSPaths.CHECKOUT
import io.gatling.core.Predef._
import io.gatling.core.feeder.FileBasedFeederBuilder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.io.Source.fromResource

object Checkout {

  val parameters: FileBasedFeederBuilder[Any]#F = jsonFile(PARAMETERS).random
  val response: FileBasedFeederBuilder[Any]#F = jsonFile(CHECKOUT_RESPONSE).random

  val checkout: ChainBuilder = feed(parameters)
    .feed(response)
    .pause(1)
    .exec(http(CHECKOUT)
      .post(CHECKOUT)
      .body(StringBody(fromResource(CHECKOUT_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").isNull)
    )
}
