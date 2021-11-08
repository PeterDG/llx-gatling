package com.leaflogix.cases

import com.leaflogix.cases.User.sessionIdFeeder
import com.leaflogix.params.DataPaths.{PARAMETERS, SEARCH_GUEST_BY_STRING_REQUEST}
import com.leaflogix.params.Identifiers.SEARCH_GUEST_BY_STRING_NAME
import com.leaflogix.params.Urls.SEARCH_GUEST_BY_STRING_PATH
import io.gatling.core.Predef.{feed, _}
import io.gatling.http.Predef._

import scala.io.Source.fromResource

object SearchGuest {
  val parameters = jsonFile(PARAMETERS).random

  val search = feed(parameters)
    .feed(sessionIdFeeder.random)
    .exec(http(SEARCH_GUEST_BY_STRING_NAME)
      .post(SEARCH_GUEST_BY_STRING_PATH)
      .header(HttpHeaderNames.SetCookie, User.cookie)
      .body(StringBody(fromResource(SEARCH_GUEST_BY_STRING_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").isNull)
    )
}
