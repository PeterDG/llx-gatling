package com.leaflogix.endpoints

import com.leaflogix.params.DataPaths.{EMPLOYEE_LOGIN_REQUEST, EMPLOYEE_LOGIN_RESPONSE, USERS}
import com.leaflogix.params.Identifiers.EMPLOYEE_LOGIN_NAME
import com.leaflogix.params.Session.{COOKIE, SESSION_ID, backendUrl, posUrl}
import com.leaflogix.params.paths.BackendPaths.EMPLOYEE_LOGIN_PATH
import io.gatling.core.Predef._
import io.gatling.core.feeder.{BatchableFeederBuilder, FileBasedFeederBuilder}
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.io.Source.fromResource

object Login {

  val users: BatchableFeederBuilder[String]#F = csv(USERS).random
  val response: FileBasedFeederBuilder[Any]#F = jsonFile(EMPLOYEE_LOGIN_RESPONSE).random

  val employeeLoginPOS: ChainBuilder = feed(users)
    .feed(response)
    .exec(http(EMPLOYEE_LOGIN_NAME)
      .post(posUrl + EMPLOYEE_LOGIN_PATH)
      .body(StringBody(fromResource(EMPLOYEE_LOGIN_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").is("${Message}"))
      .check(jsonPath("$.Data.SessionGId").ofType[String].saveAs(SESSION_ID))
      .check(headerRegex(HttpHeaderNames.SetCookie, ".*").optional.saveAs(COOKIE))
    )

  val employeeLoginBackend: ChainBuilder = feed(users)
    .feed(response)
    .exec(http(EMPLOYEE_LOGIN_NAME)
      .post(backendUrl + EMPLOYEE_LOGIN_PATH)
      .body(StringBody(fromResource(EMPLOYEE_LOGIN_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").is("${Message}"))
      .check(jsonPath("$.Data.SessionGId").ofType[String].saveAs(SESSION_ID))
      .check(headerRegex(HttpHeaderNames.SetCookie, ".*").optional.saveAs(COOKIE))
    )
}
