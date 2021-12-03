package com.leaflogix.scenarios.pos

import com.leaflogix.cases._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object CheckoutBehavior {
  def apply(): ScenarioBuilder = new CheckoutBehavior().checkout
}

class CheckoutBehavior {

  val checkout: ScenarioBuilder = scenario("CheckIn customer then checkout transaction")
    .exec(Login.employeeLogin, CheckInGuest.checkInGuest, AddItemToCart.addItemToCart, Checkout.checkout)

}