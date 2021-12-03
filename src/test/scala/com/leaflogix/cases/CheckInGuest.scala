package com.leaflogix.cases

import com.leaflogix.params.DataPaths.{CHECKIN_GUEST_REQUEST, CHECKIN_GUEST_RESPONSE, CHECKOUT_CUSTOMERS, PARAMETERS}
import com.leaflogix.params.Session.{SCAN_RESULT, SHIPMENT_ID}
import com.leaflogix.params.paths.POSPaths.CHECKIN_GUEST
import io.gatling.core.Predef._
import io.gatling.core.feeder.{BatchableFeederBuilder, FileBasedFeederBuilder}
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.io.Source.fromResource

object CheckInGuest {

  val parameters: FileBasedFeederBuilder[Any]#F = jsonFile(PARAMETERS).random
  val response: FileBasedFeederBuilder[Any]#F = jsonFile(CHECKIN_GUEST_RESPONSE).random
  val customers: BatchableFeederBuilder[String]#F = csv(CHECKOUT_CUSTOMERS).circular

  val checkInGuest: ChainBuilder = feed(parameters)
    .feed(response)
    .feed(customers)
    .exec(http(CHECKIN_GUEST)
      .post(CHECKIN_GUEST)
      .body(StringBody(fromResource(CHECKIN_GUEST_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").is("${Message}"))
      .check(jsonPath("$.Data[0].ShipmentId").ofType[String].saveAs(SHIPMENT_ID))
      .check(jsonPath("$.Data[0].ScanResult").ofType[String].saveAs(SCAN_RESULT))
    )
}
