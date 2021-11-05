package com.leaflogix.cases

import com.leaflogix.enums.BackendParams.{EMPLOYEE_LOGIN_NAME, EMPLOYEE_LOGIN_PATH, EMPLOYEE_LOGIN_REQUEST, EMPLOYEE_LOGIN_RESPONSE}
import com.leaflogix.enums.CommonParams.{COOKIE, SESSION_ID, USERS}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.io.Source.fromResource

object Login {

  val users = csv(USERS).random
  val response = jsonFile(EMPLOYEE_LOGIN_RESPONSE).random

  val employeeLogin = feed(users)
    .feed(response)
    .exec(http(EMPLOYEE_LOGIN_NAME)
      .post(EMPLOYEE_LOGIN_PATH)
      .body(StringBody(fromResource(EMPLOYEE_LOGIN_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").is("${Message}"))
      .check(jsonPath("$.Data.SessionGId").ofType[String].saveAs(SESSION_ID))
      .check(headerRegex(HttpHeaderNames.SetCookie, ".*").optional.saveAs(COOKIE))
    )
}
