package com.leaflogix.params

object Session extends Enumeration {

  val COOKIE = "Cookie"
  val SESSION_ID = "SessionId"
  val SHIPMENT_ID = "ShipmentId"
  val SCAN_RESULT = "ScanResult"
  val DATE = "Date"
  val END_DATE = "EndDate"
  val DATE_FORMAT = "MM/dd/yyyy hh:mm aa"
  val backendUrl = "https://parallel-uat.leaflogix.net"
  val posUrl = "https://parallel-pos-uat.leaflogix.net"
}