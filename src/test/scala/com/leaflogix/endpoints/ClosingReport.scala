package com.leaflogix.endpoints

import com.leaflogix.params.DataPaths.{CLOSING_REPORT_REQUEST, CLOSING_REPORT_RESPONSE, PARAMETERS}
import com.leaflogix.params.Session.{DATE, DATE_FORMAT, END_DATE}
import com.leaflogix.params.paths.BackendPaths.{BACKEND_URL, CLOSING_REPORT}
import io.gatling.core.Predef._
import io.gatling.core.feeder.FileBasedFeederBuilder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import java.text.SimpleDateFormat
import java.util.Calendar
import scala.io.Source.fromResource

object ClosingReport {

  val parameters: FileBasedFeederBuilder[Any]#F = jsonFile(PARAMETERS).random
  val response: FileBasedFeederBuilder[Any]#F = jsonFile(CLOSING_REPORT_RESPONSE).random

  val format = new SimpleDateFormat(DATE_FORMAT)
  val date: Calendar = Calendar.getInstance()
  val today: String = format.format(date.getTime)
  date.add(Calendar.DATE, 1)
  val tomorrow: String = format.format(date.getTime)

  val closingReport: ChainBuilder = feed(parameters)
    .exec(_.set(DATE, today))
    .exec(_.set(END_DATE, tomorrow))
    .feed(response)
    .exec(http(CLOSING_REPORT)
      .post(BACKEND_URL + CLOSING_REPORT)
      .body(StringBody(fromResource(CLOSING_REPORT_REQUEST).mkString))
      .asJson
      .check(status is 200)
      .check(jsonPath("$.Result").is("${Result}"))
      .check(jsonPath("$.Message").isNull)
    )
}
