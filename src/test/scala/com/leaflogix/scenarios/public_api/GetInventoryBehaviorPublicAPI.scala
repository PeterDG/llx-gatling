package com.leaflogix.scenarios.public_api

import com.leaflogix.endpoints._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object GetInventoryBehaviorPublicAPI {
  def apply(): ScenarioBuilder = new GetInventoryBehaviorPublicAPI().getInventory
}

class GetInventoryBehaviorPublicAPI {

  val getInventory: ScenarioBuilder = scenario("Get Inventory list from Public API")
    .exec(PublicAPIGetInventory.getInventory)

}