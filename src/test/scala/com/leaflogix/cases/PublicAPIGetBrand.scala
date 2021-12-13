package com.leaflogix.cases

import com.leaflogix.params.paths.PublicAPIPaths.GET_BRANDS
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object PublicAPIGetBrand {

  val getBrand: HttpRequestBuilder = http(GET_BRANDS)
    .get(GET_BRANDS)
    .check(status is 200)

}
