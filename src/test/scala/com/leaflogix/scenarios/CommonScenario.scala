package com.leaflogix.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import com.leaflogix.cases._

object CommonScenario {
  def apply(): ScenarioBuilder = new CommonScenario().scn
}

class CommonScenario {

  val scn: ScenarioBuilder = scenario("Common Scenario")
  .exec(GetBrand.getBrand)

}