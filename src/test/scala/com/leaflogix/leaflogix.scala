package com

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import ru.tinkoff.gatling.config.SimulationConfig._

package object leaflogix {
  System.out.println("URL:" + baseUrl)
  System.out.println("Auth:" + baseAuthUrl)
  val httpProtocol = http
    .baseUrl(baseUrl)
    .authorizationHeader(baseAuthUrl)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
    .contentTypeHeader("application/json")
    .disableFollowRedirect

}
