package com.leaflogix.cases

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Login {

  val feeder = csv("data/user.csv").random

  val employeeLogin = feed(feeder)
    .exec(http("Log In")
      .post("/api/posv3/user/EmployeeLogin")
      .body(StringBody("""{"username":"${username}","password":"${password}"}"""))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("true"))
      .check(jsonPath("$.Message").is("valid pos user"))
      .check(jsonPath("$.Data").optional.saveAs("employeeLoginResponse"))
      .check(jsonPath("$.Data.SessionGId").ofType[String].saveAs("SessionGId"))
      .check(headerRegex(HttpHeaderNames.SetCookie, ".*").optional.saveAs("cookie"))
    )
    .exec(User.set)
}
