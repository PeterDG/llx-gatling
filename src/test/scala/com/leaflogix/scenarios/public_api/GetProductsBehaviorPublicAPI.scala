package com.leaflogix.scenarios.public_api

import com.leaflogix.cases._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object GetProductsBehaviorPublicAPI {
  def apply(): ScenarioBuilder = new GetProductsBehaviorPublicAPI().getProducts
}

class GetProductsBehaviorPublicAPI {

  val getProducts: ScenarioBuilder = scenario("Get products list Public API")
    .exec(PublicAPIGetProducts.getProducts)

}