package com.leaflogix.endpoints

import com.leaflogix.params.paths.PublicAPIPaths.GET_PRODUCTS
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object PublicAPIGetProducts {

  val getProducts: HttpRequestBuilder = http(GET_PRODUCTS)
    .get(GET_PRODUCTS)
    .check(status is 200)

}
