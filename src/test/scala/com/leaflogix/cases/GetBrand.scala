package com.leaflogix.cases

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetBrand {

  val getBrand = http("GET /brand")
    .get("/brand")
    .check(status is 200)

}
