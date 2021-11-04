package com.leaflogix.simulations.public_api

import com.leaflogix.httpProtocol
import com.leaflogix.scenarios.public_api.GetBrandsList
import io.gatling.core.Predef._
import ru.tinkoff.gatling.config.SimulationConfig._
import ru.tinkoff.gatling.influxdb.Annotations

class MaxPerformance extends Simulation with Annotations {

  setUp(
    GetBrandsList().inject(
      incrementUsersPerSec((intensity / stagesNumber).toInt)
        .times(stagesNumber)
        .eachLevelLasting(stageDuration)
        .separatedByRampsLasting(rampDuration)
        .startingFrom(0)
    )
  ).protocols(httpProtocol)
    .maxDuration(testDuration)

}
