package com.leaflogix.scenarios.backend

import com.leaflogix.cases._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object ClosingReportBehavior {
  def apply(): ScenarioBuilder = new ClosingReportBehavior().closingReport
}

class ClosingReportBehavior {

  val closingReport: ScenarioBuilder = scenario("User generates closing report")
    .exec(Login.employeeLogin, ClosingReport.closingReport)

}