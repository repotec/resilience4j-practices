@echo off

SET DEVELOPMENT_HOME=..\..\..\..\

cd %DEVELOPMENT_HOME%\rate-limiter\

call mvn clean package

cd C:\Users\ahmed\git\resilience4j-practices\rate-limiter\target

java -jar rate-limiter-0.0.1-SNAPSHOT.jar --server.port=8085

Cmd /k
