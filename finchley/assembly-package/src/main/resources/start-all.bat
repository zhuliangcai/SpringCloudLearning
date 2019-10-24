@echo off
start java -jar eureka-server.jar --server.port=8761

@echo off
start java -jar service-hi.jar --server.port=8762

@echo off
start java -jar service-ribbon.jar --server.port=8763

@echo off
start java -jar serice-feign.jar --server.port=8764

rem start java -jar serice-feign-hystrix.jar --server.port=8761

@echo off
start java -jar service-zuul.jar --server.port=8765


rem @echo off
rem start java -jar config-server.jar --server.port=8888

rem @echo off
rem start java -jar config-client.jar --server.port=8881

rem pause