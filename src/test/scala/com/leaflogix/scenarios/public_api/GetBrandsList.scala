package com.leaflogix.scenarios.public_api

import com.leaflogix.cases._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object GetBrandsList {
  def apply(): ScenarioBuilder = new GetBrandsList().brands
}

class GetBrandsList {

  val brands: ScenarioBuilder = scenario("Get brands list")
    .exec(GetBrand.getBrand)

}