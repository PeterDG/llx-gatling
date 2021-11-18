export JAVA_OPTS = "-DbaseUrl=https://parallel-uat.leaflogix.net"
sbt "Gatling / testOnly com.leaflogix.simulations.backend.Stability"