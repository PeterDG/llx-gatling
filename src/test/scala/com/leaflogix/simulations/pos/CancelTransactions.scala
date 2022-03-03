package com.leaflogix.simulations.pos

import com.leaflogix.httpProtocol
import com.leaflogix.scenarios.pos.CancelTransactionBehavior
import io.gatling.core.Predef._
import ru.tinkoff.gatling.influxdb.Annotations

class CancelTransactions extends Simulation with Annotations {
  setUp(
    CancelTransactionBehavior().inject(atOnceUsers(101))
  ).protocols(httpProtocol)
}
