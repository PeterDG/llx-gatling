package com.leaflogix.endpoints

import com.leaflogix.params.DataPaths.{PARAMETERS, PRODUCT_SEARCH_REQUEST, PRODUCT_SEARCH_RESPONSE}
import com.leaflogix.params.Session.posUrl
import com.leaflogix.params.paths.POSPaths.PRODUCT_SEARCH
import io.gatling.core.Predef._
import io.gatling.core.feeder.FileBasedFeederBuilder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.io.Source.fromResource

object ProductSearch {

  val parameters: FileBasedFeederBuilder[Any]#F = jsonFile(PARAMETERS).random
  val response: FileBasedFeederBuilder[Any]#F = jsonFile(PRODUCT_SEARCH_RESPONSE).random

  val productSearch: ChainBuilder = feed(parameters)
    .feed(response)
    .exec(http(PRODUCT_SEARCH)
      .post(posUrl + PRODUCT_SEARCH)
      .body(StringBody(fromResource(PRODUCT_SEARCH_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").isNull))
}