@echo off

set MAVEN_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9000

mvn -Djetty.port=9000 jetty:run