package com.leaflogix.scenarios.public_api

import com.leaflogix.cases._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object GetBrandsBehaviorPublicAPI {
  def apply(): ScenarioBuilder = new GetBrandsBehaviorPublicAPI().brands
}

class GetBrandsBehaviorPublicAPI {

  val brands: ScenarioBuilder = scenario("Get brands list from Public API")
    .exec(PublicAPIGetBrand.getBrand)

}