package com.leaflogix.scenarios.pos

import com.leaflogix.cases._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object GuestListSearchByString {
  def apply(): ScenarioBuilder = new GuestListSearchByString().search
}

class GuestListSearchByString {

  val search: ScenarioBuilder = scenario("Search for users by string in the guest list")
    .exec(Login.employeeLogin, SearchGuest.search)

}