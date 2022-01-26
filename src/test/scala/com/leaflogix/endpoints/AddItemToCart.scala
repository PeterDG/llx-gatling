package com.leaflogix.endpoints

import com.leaflogix.params.DataPaths.{ADD_ITEM_TO_CART_REQUEST, ADD_ITEM_TO_CART_RESPONSE, PARAMETERS}
import com.leaflogix.params.paths.POSPaths.{ADD_ITEM_TO_CART, POS_URL}
import io.gatling.core.Predef._
import io.gatling.core.feeder.FileBasedFeederBuilder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.io.Source.fromResource

object AddItemToCart {

  val parameters: FileBasedFeederBuilder[Any]#F = jsonFile(PARAMETERS).random
  val response: FileBasedFeederBuilder[Any]#F = jsonFile(ADD_ITEM_TO_CART_RESPONSE).random

  val addItemToCart: ChainBuilder = feed(parameters)
    .feed(response)
    .exec(http(ADD_ITEM_TO_CART)
      .post(POS_URL + ADD_ITEM_TO_CART)
      .body(StringBody(fromResource(ADD_ITEM_TO_CART_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").is("${Message}"))
    )
}
