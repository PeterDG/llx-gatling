package com.leaflogix.scenarios.pos

import com.leaflogix.endpoints._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object ProductSearchBehavior {
  def apply(): ScenarioBuilder = new ProductSearchBehavior().productSearch
}

class ProductSearchBehavior {

  val productSearch: ScenarioBuilder = scenario("Search for products after checkin guest")
    .exec(Login.employeeLoginPOS, CheckInGuest.checkInGuest, ProductSearch.productSearch)

}