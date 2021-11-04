package com.leaflogix.cases

import io.gatling.core.Predef._

object User {
  var sessionId = ""
  var cookie = ""
  val set = exec { session =>
    sessionId = session("SessionGId").as[String]
    cookie = session("cookie").as[String]
    session
  }

}
