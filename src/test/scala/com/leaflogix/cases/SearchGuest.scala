package com.leaflogix.cases

import com.leaflogix.cases.User.sessionIdFeeder
import com.leaflogix.data.Common.PARAMETERS
import com.leaflogix.data.Pos.{SEARCH_GUEST_BY_STRING_NAME, SEARCH_GUEST_BY_STRING_PATH, SEARCH_GUEST_BY_STRING_REQUEST}
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
      .check(jsonPath("$").ofType[String].saveAs("response"))
    )
    .exec { session =>
      val response = session("response").as[String]
      println("Response:" + response)
      session
    }
}
