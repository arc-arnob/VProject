# SPRING BOOT MICROCSERVICE USING SPRING CLOUD, OAUTH,JWT, EUREKA, RIBBON, ZUUL, DOCKER
This project is created to get experience on Microservices With Netflix OSS. This is a simple project by coded imperative programming with simple business requirements.

The project have been dockerized

There are 7 microservices:
Zuul-server : This microservice is the front door for all requests from devices and websites to the backend of the application.
Auth-server :  this microservice delegates the operations of sign-in, sign-out, and password recovery to a separate service (also called identity federation) 
Eureka-server : This microservice holds the information about all client-service applications. Every Micro service will register into the Eureka server and Eureka server knows all the client applications running on each port and IP address
Movie-service: This microservice is provides the List of all the movies present
Rating-service: This movie stores the ratings for each movie that user provides.
Movie-catalog-service: This microservice uses calls endpoints of Movie-service and Rating-service to execute business logic. 
Product-api: This acts as an gate way to the actual application and have authorizaions enabled
Movies-catalog-service microservice has an endpoint with path "/api/catalog/showratedmovies/{urlId}". This endpoint return a list of movies with name description and rating.

EndPoints
Service	EndPoint	Method	Description
Accounts	/api/v1/accounts/{id}	GET	Return detail of specified account
Accounts	/api/v1/accounts	GET	Return details of all acounts
Products	/api/v1/products/{id}	GET	Return detail of specified product
Products	/api/v1/products	GET	Return details of all products
Orders	/api/v1/orders/{id}	GET	Return detail of order
Orders	/api/v1/orders	GET	Return details of orders
Backoffice	/api/v1/backoffice/orders	GET	Return orders with product name and account name
Gateways
Service	EndPoint
Accounts	/account/api/v1/accounts/{id}
Accounts	/account/api/v1/accounts
Products	/product/api/v1/products/{id}
Products	/product/api/v1/products
Orders	/order/api/v1/orders/{id}
Orders	/order/api/v1/orders
Backoffice	/backoffice/api/v1/backoffice/orders
URI for gateway : http://localhost:8762

Used Netflix OSS:
Netflix Eureka is used for discovery service.
Netflix Ribbon is used for client side load-balancing.
Netflix Zuul is used for gateway.
Used ELK STACK:
ElasticSearch is on 6972 port
Logstash TCP is on 5000 port
Kibana is on 5601 port
Build & Run
>mvn clean package : to build
>docker-compose up --build : build docker images and containers and run containers
>docker-compose stop : stop the dockerized services
Each maven module has a Dockerfile.
In docker-compose.yml file:

Accounts Service : 2222 port is mapped to 7500 port of host
Products Service : 2222 port is mapped to 7501 port of host
Orders Service : 2222 port is mapped to 7502 port of host
Backoffice Service : 2222 port is mapped to 7503 port of host
Eureka Discovery Service : 8761 port is mapped to 8761 port of host
Zuul Gateway Service : 8762 port is mapped to 8762 port of host
VERSIONS
1.1.0
ElasticSearch, Kibana, Logstash integration
1.0.0 SNAPSHOT
Spring-Boot 2.3.1.RELEASE
Java 11
Docker Image updated
Spring-Cloud artifacts have been changed
Open Feign integrated
0.0.1
Spring-Boot 2.0.2.RELEASE
Java 8
