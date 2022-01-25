package com.leaflogix.scenarios.backend

import com.leaflogix.endpoints._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object GetInventoryBehavior {
  def apply(): ScenarioBuilder = new GetInventoryBehavior().getInventory
}

class GetInventoryBehavior {

  val getInventory: ScenarioBuilder = scenario("User gets inventory")
    .exec(Login.employeeLoginBackend, GetInventory.getInventory)

}