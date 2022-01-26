package com.leaflogix.scenarios.backend

import com.leaflogix.endpoints._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object ReceiveBehavior {
  def apply(): ScenarioBuilder = new ReceiveBehavior().receive
}

class ReceiveBehavior {

  val receive: ScenarioBuilder = scenario("User receive package")
    .exec(Login.employeeLoginBackend, Receive.receive)

}