# Gatling Leaflogix Project

Leaflogix project for gatling performance tests


## Project structure

```
src.test.resources - project resources
src.test.scala.com.leaflogix.cases - simple cases
src.test.scala.com.leaflogix.scenarios - common load scenarios assembled from simple cases
src.test.scala.com.leaflogix - common test configs
```

## Test configuration

Pass this params to JVM using -DparamName="paramValue" AND -Dconfig.override_with_env_vars=true

```
Gatling logs:
CONSOLE_LOGGING=ON - turn on console logging
FILE_LOGGING=ON - turn on logging in file "target/gatling/gatling.log"
GRAYLOG_LOGGING=ON - turn on logging in graylog
    graylog params:
        GRAYLOG_HOST - graylog host
        GRAYLOG_PORT - on which port graylog input is
        GRAYLOG_STREAM - name of graylog stream

Gatling metrics in influxdb:
GRAPHITE_HOST - influxdb with configured graphite plugin host
GRAPHITE_PORT - see /etc/influxdb/influxdb.conf: bind-address
INFLUX_PREFIX - see /etc/influxdb/influxdb.conf: database
```

Also you can pass all params from gatling-picatinny or use custom params
read: https://github.com/TinkoffCreditSystems/gatling-picatinny/blob/master/README.md

## Debug

1. Debug test with 1 user, requires proxy on localhost:8888, eg using Fiddler or Wireshark

```
sbt "Gatling / testOnly com.leaflogix.simulations.public_api.Debug"
```

2. Run test from IDEA with breakpoints

```
com.GatlingRunner
```

## Launch test

```
sbt "Gatling / testOnly com.leaflogix.simulations.public_api.MaxPerformance" - maximum performance test
sbt "Gatling / testOnly com.leaflogix.simulations.public_api.Stability" - stability test
```