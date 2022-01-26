package com.leaflogix.scenarios.public_api

import com.leaflogix.endpoints._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object GetReportingTransactionsBehaviorPublicAPI {
  def apply(): ScenarioBuilder = new GetReportingTransactionsBehaviorPublicAPI().getReportingTransactions
}

class GetReportingTransactionsBehaviorPublicAPI {

  val getReportingTransactions: ScenarioBuilder = scenario("Get reporting transactions from public API")
    .exec(PublicAPIGetReportingTransactions.getReportingTransactions)

}