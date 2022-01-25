package com.leaflogix.endpoints

import com.leaflogix.params.DataPaths.{PARAMETERS, SEARCH_GUEST_BY_STRING_REQUEST, SEARCH_GUEST_BY_STRING_RESPONSE}
import com.leaflogix.params.Identifiers.SEARCH_GUEST_BY_STRING_NAME
import com.leaflogix.params.Session.posUrl
import com.leaflogix.params.paths.POSPaths.SEARCH_GUEST_BY_STRING_PATH
import io.gatling.core.Predef.{feed, _}
import io.gatling.core.feeder.FileBasedFeederBuilder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.io.Source.fromResource

object SearchGuest {
  val parameters: FileBasedFeederBuilder[Any]#F = jsonFile(PARAMETERS).random
  val response: FileBasedFeederBuilder[Any]#F = jsonFile(SEARCH_GUEST_BY_STRING_RESPONSE).random

  val search: ChainBuilder = feed(parameters)
    .feed(response)
    .exec(http(SEARCH_GUEST_BY_STRING_NAME)
      .post(posUrl + SEARCH_GUEST_BY_STRING_PATH)
      .body(StringBody(fromResource(SEARCH_GUEST_BY_STRING_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").isNull)
    )
}
