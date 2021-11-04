package com.leaflogix.simulations.public_api

import com.leaflogix.httpProtocol
import com.leaflogix.scenarios.public_api.GetBrandsList
import io.gatling.core.Predef._
import ru.tinkoff.gatling.config.SimulationConfig._
import ru.tinkoff.gatling.influxdb.Annotations

class Stability extends Simulation with Annotations {
  setUp(
    GetBrandsList().inject(
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      constantUsersPerSec(intensity.toInt) during stageDuration
    )
  ).protocols(httpProtocol)
    .maxDuration(testDuration)

}
