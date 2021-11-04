package com.leaflogix.simulations.public_api

import com.leaflogix.httpProtocol
import com.leaflogix.scenarios.public_api.GetBrandsList
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import ru.tinkoff.gatling.config.SimulationConfig._

class Debug extends Simulation {

  // proxy is required on localhost:8888

  setUp(
    GetBrandsList().inject(atOnceUsers(1))
  ).protocols(
    httpProtocol
      .proxy(Proxy("localhost", 8888).httpsPort(8888))
  )
    .maxDuration(testDuration)

}
