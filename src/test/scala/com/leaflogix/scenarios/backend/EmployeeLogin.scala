package com.leaflogix.scenarios.backend

import com.leaflogix.endpoints._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object EmployeeLogin {
  def apply(): ScenarioBuilder = new EmployeeLogin().user
}

class EmployeeLogin {

  val user: ScenarioBuilder = scenario("User Log In")
    .exec(Login.employeeLoginBackend)

}