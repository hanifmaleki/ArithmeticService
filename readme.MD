#  Arithmetic Service
This project is a sample for [**RESTful** web-service](https://en.wikipedia.org/wiki/Representational_state_transfer) as well as a [**messaging** system](https://en.wikipedia.org/wiki/Enterprise_messaging_system).
It consists of a server-side which presents web-service endpoints, store and process requests and a
client side with [**AngularJS**](https://angularjs.org/) that checks the format of requests and call the RESTful web-service.

## Description
With this project, one is able to create simple binary operation requests on the *positive* integers.
 The requests send to the server through some RESTful calls and the results are returned by means of
 some pulling requests.

## Prequisits
For running this project an installation of the **Redis** database server is needed.

## Usage Instruction
The project is uploaded to the following address:
https://hanifarithmetic.herokuapp.com/

In order to execute it locally, it is enough to download the project and run it by
```
gradlew bootRun
```
You can see the web user interface in:
http://localhost:8080/
If the Redis server is installed on the non-default *host* or *port*, change the values in __*src/main/resources/application.properties*__
```
spring.redis.host=localhost
spring.redis.port=6379
```

## Built with
- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Redis](https://redis.io/)
- [Gradle](https://gradle.org/)

## Authors
- Hanif Maleki hanif.maleki@gmail.com

## Licences
Arithmetic Service is freely distributed under the MIT License. See LICENSE for details.
