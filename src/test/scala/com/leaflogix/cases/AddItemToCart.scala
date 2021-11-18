package com.leaflogix.cases

import com.leaflogix.cases.User.{scanResultFeeder, sessionIdFeeder, shipmentIdFeeder}
import com.leaflogix.params.DataPaths.{ADD_ITEM_TO_CART_REQUEST, ADD_ITEM_TO_CART_RESPONSE, PARAMETERS}
import com.leaflogix.params.paths.POSPaths.ADD_ITEM_TO_CART
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
    .feed(sessionIdFeeder.random)
    .feed(shipmentIdFeeder.circular)
    .feed(scanResultFeeder.circular)
    .exec(http(ADD_ITEM_TO_CART)
      .post(ADD_ITEM_TO_CART)
      .header(HttpHeaderNames.SetCookie, User.cookie)
      .body(StringBody(fromResource(ADD_ITEM_TO_CART_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").is("${Message}"))
    )
}
