package com.leaflogix.simulations.pos

import com.leaflogix.httpProtocol
import com.leaflogix.scenarios.pos.{CancelTransactionBehavior, CheckoutBehavior, GuestListSearchByString, ProductSearchBehavior}
import io.gatling.core.Predef._
import ru.tinkoff.gatling.config.SimulationConfig._
import ru.tinkoff.gatling.influxdb.Annotations

class Stability extends Simulation with Annotations {
  setUp(
    GuestListSearchByString().inject(
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      constantUsersPerSec(intensity.toInt) during stageDuration,
    ),
    CancelTransactionBehavior().inject(
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      constantUsersPerSec(intensity.toInt) during stageDuration,
    ),
    CheckoutBehavior().inject(
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      constantUsersPerSec(intensity.toInt) during stageDuration,
    ),
    ProductSearchBehavior().inject(
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      constantUsersPerSec(intensity.toInt) during stageDuration,
    )
  ).protocols(httpProtocol)
    .maxDuration(testDuration)

}
