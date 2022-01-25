package com.leaflogix.simulations.pos

import com.leaflogix.httpProtocol
import com.leaflogix.scenarios.pos.{CancelTransactionBehavior, CheckoutBehavior, GuestListSearchByString, ProductSearchBehavior}
import io.gatling.core.Predef._
import ru.tinkoff.gatling.config.SimulationConfig._
import ru.tinkoff.gatling.influxdb.Annotations

class Stability extends Simulation with Annotations {
  setUp(
    GuestListSearchByString().inject(
      constantConcurrentUsers(intensity.toInt).during(stageDuration),
    ),
    CancelTransactionBehavior().inject(
      constantConcurrentUsers(intensity.toInt).during(stageDuration),
    ),
    CheckoutBehavior().inject(
      constantConcurrentUsers(intensity.toInt).during(stageDuration),
    ),
    ProductSearchBehavior().inject(
      constantConcurrentUsers(intensity.toInt).during(stageDuration),
    )
  ).protocols(httpProtocol)
    .maxDuration(testDuration)
}
