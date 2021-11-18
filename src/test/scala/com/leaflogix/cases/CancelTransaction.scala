package com.leaflogix.cases

import com.leaflogix.cases.User.sessionIdFeeder
import com.leaflogix.params.DataPaths.{CANCEL_TRANSACTION_REQUEST, CANCEL_TRANSACTION_RESPONSE, PARAMETERS}
import com.leaflogix.params.paths.POSPaths.CANCEL_TRANSACTION
import io.gatling.core.Predef._
import io.gatling.core.feeder.FileBasedFeederBuilder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.io.Source.fromResource

object CancelTransaction {

  val parameters: FileBasedFeederBuilder[Any]#F = jsonFile(PARAMETERS).random
  val response: FileBasedFeederBuilder[Any]#F = jsonFile(CANCEL_TRANSACTION_RESPONSE).random

  val cancelTransaction: ChainBuilder = feed(parameters)
    .feed(response)
    .feed(sessionIdFeeder.random)
    .exec(http(CANCEL_TRANSACTION)
      .post(CANCEL_TRANSACTION)
      .header(HttpHeaderNames.SetCookie, User.cookie)
      .body(StringBody(fromResource(CANCEL_TRANSACTION_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").is("${Message}"))
    )
}
