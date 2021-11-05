package com.leaflogix.cases

import com.leaflogix.enums.CommonParams.{COOKIE, SESSION_ID}
import io.gatling.core.Predef._

object User {

  var sessionId = ""
  var cookie = ""
  var sessionIdFeeder = Array(Map(SESSION_ID -> sessionId))
  val set = exec { session =>
    sessionId = session(SESSION_ID).as[String]
    cookie = session(COOKIE).as[String]
    sessionIdFeeder = Array(Map(SESSION_ID -> sessionId))
    session
  }

}
