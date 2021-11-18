package com.leaflogix.cases

import com.leaflogix.params.Session.{COOKIE, SCAN_RESULT, SESSION_ID, SHIPMENT_ID}
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder

object User {

  var sessionId = ""
  var cookie = ""
  var shipmentId = ""
  var scanResult = ""
  var sessionIdFeeder: Array[Map[String, String]] = Array(Map(SESSION_ID -> sessionId))
  var shipmentIdFeeder: Array[Map[String, String]] = Array(Map(SHIPMENT_ID -> shipmentId))
  var scanResultFeeder: Array[Map[String, String]] = Array(Map(SCAN_RESULT -> scanResult))

  val set: ChainBuilder = exec { session =>
    sessionId = session(SESSION_ID).as[String]
    cookie = session(COOKIE).as[String]
    shipmentId = session(SHIPMENT_ID).as[String]
    scanResult = session(SCAN_RESULT).as[String]
    sessionIdFeeder = Array(Map(SESSION_ID -> sessionId))
    shipmentIdFeeder = Array(Map(SHIPMENT_ID -> shipmentId))
    scanResultFeeder = Array(Map(SCAN_RESULT -> scanResult))
    session
  }
}
