package com

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import ru.tinkoff.gatling.config.SimulationConfig._

package object leaflogix {
  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .authorizationHeader(baseAuthUrl)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
    .contentTypeHeader("application/json")
    .disableFollowRedirect

}
