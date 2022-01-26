package com.leaflogix.endpoints

import com.leaflogix.params.DataPaths.{GET_INVENTORY_REQUEST, GET_INVENTORY_RESPONSE, PARAMETERS}
import com.leaflogix.params.paths.BackendPaths.{BACKEND_URL, GET_INVENTORY}
import io.gatling.core.Predef._
import io.gatling.core.feeder.FileBasedFeederBuilder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.io.Source.fromResource

object GetInventory {

  val parameters: FileBasedFeederBuilder[Any]#F = jsonFile(PARAMETERS).random
  val response: FileBasedFeederBuilder[Any]#F = jsonFile(GET_INVENTORY_RESPONSE).random

  val getInventory: ChainBuilder = feed(parameters)
    .feed(response)
    .exec(http(GET_INVENTORY)
      .post(BACKEND_URL + GET_INVENTORY)
      .body(StringBody(fromResource(GET_INVENTORY_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").is("${Message}"))
    )
}
