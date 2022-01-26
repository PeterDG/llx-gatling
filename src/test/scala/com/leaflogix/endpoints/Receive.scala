package com.leaflogix.endpoints

import com.leaflogix.params.DataPaths.{PARAMETERS, RECEIVE_REQUEST, RECEIVE_RESPONSE}
import com.leaflogix.params.paths.BackendPaths.{BACKEND_URL, RECEIVE}
import io.gatling.core.Predef._
import io.gatling.core.feeder.FileBasedFeederBuilder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.io.Source.fromResource

object Receive {

  val parameters: FileBasedFeederBuilder[Any]#F = jsonFile(PARAMETERS).random
  val response: FileBasedFeederBuilder[Any]#F = jsonFile(RECEIVE_RESPONSE).random

  val receive: ChainBuilder = feed(parameters)
    .feed(response)
    .exec(http(RECEIVE)
      .post(BACKEND_URL + RECEIVE)
      .body(StringBody(fromResource(RECEIVE_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").isNull)
    )
}
