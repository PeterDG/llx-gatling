package com.leaflogix.cases

import io.gatling.core.Predef.{feed, _}
import io.gatling.http.Predef._

object SearchGuest {
  val feeder = jsonFile("data/parameters.json").random

  val search = feed(feeder)
    .exec(http("Search guest")
      .post("/api/v2/guest/checkin_search_by_string")
      .header(HttpHeaderNames.SetCookie, User.cookie)
      .body(StringBody("""{"LspId": "${LspId}","LocId": "${LocId}","Register": "${Register}","UserId": "${UserId}","SearchString": "${SearchString}","SessionId":"""" + User.sessionId + """"}"""))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("true"))
      .check(jsonPath("$.Message").isNull)
      .check(jsonPath("$.Data").ofType[String].saveAs("searchGuest"))
    )
    .exec(session => {
      println("SessionId:" + User.sessionId)
      println("searchGuest:" + session("searchGuest").as[String])
      session
    })
}
