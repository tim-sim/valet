@echo off

set MAVEN_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9000

mvn jetty:run