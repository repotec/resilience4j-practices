@echo off

SET DEVELOPMENT_HOME=..\..\..\..\bulkhead
cd %DEVELOPMENT_HOME%
echo start packaging from %cd%
call mvn clean package


SET TARGET=%cd%\target
cd %TARGET%
echo start deploying from %TARGET%

java -jar bulkhead-0.0.1-SNAPSHOT.jar --server.port=8086

Cmd /k
