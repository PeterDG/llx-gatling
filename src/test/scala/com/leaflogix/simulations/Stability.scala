package com.leaflogix.simulations

import com.leaflogix.httpProtocol
import com.leaflogix.scenarios.backend.{ClosingReportBehavior, GetInventoryBehavior, ReceiveBehavior}
import com.leaflogix.scenarios.pos.{CancelTransactionBehavior, CheckoutBehavior, GuestListSearchByString, ProductSearchBehavior}
import com.leaflogix.scenarios.public_api.{GetBrandsBehaviorPublicAPI, GetInventoryBehaviorPublicAPI, GetProductsBehaviorPublicAPI, GetReportingTransactionsBehaviorPublicAPI}
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
    ),
    GetInventoryBehavior().inject(
      constantConcurrentUsers(intensity.toInt).during(stageDuration),
    ),
    ReceiveBehavior().inject(
      constantConcurrentUsers(intensity.toInt).during(stageDuration),
    ),
    ClosingReportBehavior().inject(
      constantConcurrentUsers(intensity.toInt).during(stageDuration),
    ),
    GetBrandsBehaviorPublicAPI().inject(
      constantConcurrentUsers(intensity.toInt).during(stageDuration),
    ),
    GetInventoryBehaviorPublicAPI().inject(
      constantConcurrentUsers(intensity.toInt).during(stageDuration),
    ),
    GetProductsBehaviorPublicAPI().inject(
      constantConcurrentUsers(intensity.toInt).during(stageDuration),
    ),
    GetReportingTransactionsBehaviorPublicAPI().inject(
      constantConcurrentUsers(intensity.toInt).during(stageDuration),
    )
  ).protocols(httpProtocol)
    .maxDuration(testDuration)
}
