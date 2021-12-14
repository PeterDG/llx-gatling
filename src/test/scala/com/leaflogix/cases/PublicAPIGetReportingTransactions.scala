package com.leaflogix.cases

import com.leaflogix.params.Session.DATE_FORMAT
import com.leaflogix.params.paths.PublicAPIPaths.GET_REPORTING_TRANSACTIONS
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

import java.text.SimpleDateFormat
import java.util.Calendar

object PublicAPIGetReportingTransactions {

  val format = new SimpleDateFormat(DATE_FORMAT)
  val date: Calendar = Calendar.getInstance()
  val today: String = format.format(date.getTime)
  date.add(Calendar.DATE, 1)
  val tomorrow: String = format.format(date.getTime)
  val FROM_LAST_MODIFIED_DATE_UTC = "fromLastModifiedDateUTC";
  val TO_LAST_MODIFIED_DATE_UTC = "toLastModifiedDateUTC"

  val getReportingTransactions: HttpRequestBuilder = http(GET_REPORTING_TRANSACTIONS)
    .get(GET_REPORTING_TRANSACTIONS)
    .queryParam(FROM_LAST_MODIFIED_DATE_UTC, today)
    .queryParam(TO_LAST_MODIFIED_DATE_UTC, tomorrow)
    .check(status is 200)

}