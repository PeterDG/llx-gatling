package com.leaflogix.scenarios.pos

import com.leaflogix.endpoints._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object CancelTransactionBehavior {
  def apply(): ScenarioBuilder = new CancelTransactionBehavior().cancelTransaction
}

class CancelTransactionBehavior {

  val cancelTransaction: ScenarioBuilder = scenario("CheckIn customer then cancel transaction")
    .exec(Login.employeeLoginPOS, CheckInGuest.checkInGuest, CancelTransaction.cancelTransaction)

}