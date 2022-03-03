# Gatling Leaflogix Project

Leaflogix project for gatling performance tests


## Project structure

```
src.test.resources - project resources
src.test.scala.com.leaflogix.endpoints - reusables endpoints files
src.test.scala.com.leaflogix.scenarios - common load scenarios assembled from endpoints 
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

## Launch test with default values

```
Public API scenarios
sbt "Gatling / testOnly com.leaflogix.simulations.public_api.Stability"

Backend scenarios
sbt "Gatling / testOnly com.leaflogix.simulations.backend.Stability"

POS scenarios
sbt "Gatling / testOnly com.leaflogix.simulations.pos.Stability"

All Scenarios
sbt "Gatling / testOnly com.leaflogix.simulations.Stability"

Cancel Transactions 
sbt "Gatling / testOnly com.leaflogix.simulations.pos.CancelTransactions"
```

## Launch test with custom values

```
SET JAVA_OPTS=-DbaseUrl=https://leaflogix-env-test.azurewebsites.net"
sbt "Gatling / testOnly com.leaflogix.simulations.pos.Stability"
or
Adding java options parameter to IDE run setup 
JAVA_OPTS=-DCONSOLE_LOGGING=ON -Xloggc:garbage-collection.log -XX:+PrintGCTimeStamps -XX:+PrintGCDetails
```