package com.leaflogix.endpoints

import com.leaflogix.params.paths.PublicAPIPaths.GET_INVENTORY
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object PublicAPIGetInventory {

  val getInventory: HttpRequestBuilder = http(GET_INVENTORY)
    .get(GET_INVENTORY)
    .check(status is 200)

}
