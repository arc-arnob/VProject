# SPRING BOOT MICROCSERVICE USING SPRING CLOUD, OAUTH,JWT, EUREKA, RIBBON, ZUUL, DOCKER
This project is created to get experience on Microservices With OAuth, JWT etc. This is a simple project by coded imperative programming with simple business requirements.

The project have been dockerized

## There are 7 microservices:
* **Zuul-server** : This microservice is the front door for all requests from devices and websites to the backend of the application.
* **Auth-server** :  this microservice delegates the operations of sign-in, sign-out, and password recovery to a separate service (also called identity federation) 
* **Eureka-server**  : This microservice holds the information about all client-service applications. Every Micro service will register into the Eureka server and Eureka server knows all the client applications running on each port and IP address
* **Movie-service** : This microservice is provides the List of all the movies present
* **Rating-service** : This movie stores the ratings for each movie that user provides.
* **Movie-catalog-service** : This microservice uses calls endpoints of Movie-service and Rating-service to execute business logic. 
* **Product-api** : This acts as an gate way to the actual application and have authorizaions enabled

 Movies-catalog-service microservice has an endpoint with path "/api/catalog/showratedmovies/{urlId}". This endpoint return a list of movies with name description and rating.


## ENDPOINTS
Service |	EndPoint |	Method |	Description
------- | -------  | ------- | -----------
product-service-api | /movies/{urlId} |	GET	| Return list of all movies
product-service-api | /userratings/{urlId} |	GET	| Return list of all movies of a particular user
product-service-api | /addratings/{urlId} |	POST	| Sends rating of a particular user of the movie
product-service-api | /addmovies/{urlId} |	POST	| Adds new Movie to the database
movie-catalog-service |	/catalog/showmovies |	GET	| Return List of all movies
movie-catalog-service |	/catalog/showratedmovie/{userId} |	GET	| Return List of all rated movies with rating
movie-catalog-service |	/catalog/ratemovie |	POST	| Adds Rating to List of all rated movies with rating
movie-catalog-service |	/catalog/addmovie |	POST	| Adds Movie to List of all movies
movie-service-api |	/movieservice/allmovies |	GET	| Returns List of all movies
movie-service-api |	/movieservice/movie/{movieId} |	GET	| Returns List of all movies with given Id
movie-service-api |	/movieservice/addmovie |	POST	| Posts details of a movie to be added to database
movie-service-api |	/movieservice/updatemovie/{movieId} |	POST	| Posts details of a movie to be added/updated to database
movie-service-api |	/movieservice/deleteMovie/{movieId} |	POST	| Posts details of a movie to be deleted from database
rating-service-api |	/rating/service/deleteMovie/{movieId} |	POST	| Posts details of a movie to be deleted from database
rating-service-api |	/rating/allratings/{userId} |	GET	| Get List of all rated movies
rating-service-api |	/rating/addrating |	POST | Post a new rating to the database
rating-service-api |	/rating/updaterating/{ratingId} |	POST | Update any exisiting Rating information
rating-service-api |	/rating/deleterating/{ratingId} |	POST | Delete any exisiting Rating information

## GATEWAYS
Service |	EndPoint
------- | ------- 
auth-server	| /api/authserver/oauth/token
auth-server	| /api/authserver/oauth/check_token
product-service-api	| /api/resource-server-api/addmovies/addmovie
product-service-api	| /api/resource-server-api/movies/showmovies/{userId}
product-service-api	| /api/resource-server-api/addratings/ratemovie
product-service-api	| /api/resource-server-api/userratings/{userId}
product-service-api	| /api/resource-server-api/showmoviesbyid/{movieId}

URI for gateway : http://localhost:8763
URI for movie-catalog-service : http://localhost:8081
URI for movie-service-api : http://localhost:9090
URI for rating-service-api : http://localhost:9091
URI for auth-server : http://localhost:9999
URI for eureka-server : http://localhost:8761
URI for zuul-server : http://localhost:8763

## Used Netflix OSS:
* Netflix Eureka is used for discovery service.
* Netflix Ribbon is used for client side load-balancing.
* Netflix Zuul is used for gateway.

## Build & Run
* > mvn clean package : to build
* > docker-compose up --build : build docker images and containers and run containers
* > docker-compose stop : stop the dockerized services
* Each maven module has a Dockerfile.

In docker-compose.yml file:

* discovery-service : 8761 port is mapped to 8761 port of host
* auth-server : 9999 port is mapped to 9999 port of host
* movie-service-api : 9090 port is mapped to 9090 port of host
* rating-service-api : 9091 port is mapped to 9091 port of host
* movie-catalog-service : 8081 port is mapped to 8081 port of host
* product-service-api : 8080 port is mapped to 8080 port of host
* zuul-server : 8763 port is mapped to 8763 port of host 

## VERSIONS

* 1.0.0 SNAPSHOT
* Spring-Boot 2.3.3.RELEASE
* Java 11
* Docker Image updated
* MySql 8
* Spring-Cloud artifacts

## Run on your PC without Docker

### What Will You Build
You will build a web application that is Oauth2 enabled.

### What you will need

* A favorite text editor or IDE
* JDK 1.8 or later
* Gradle 4+ or Maven 3.2+

### To clone and the project do the following
* Download and unzip the source repository for this guide, or clone it using Git: git clone https://github.com/arc-arnob/VProject.git
* cd to /target folders of all the spring boot applications and run: mvnw spring-boot:run

### MySql Workbench Configuration
Make the following configuration changes in the application.properties file to make it run with your local sql server
* File Location: VProject/Support/auth-server/auth-server/src/main/resources/application.yml	
    * Configuration:
    ```
    datasource :url: jdbc:mysql://localhost:3306/authdb?createDatabaseIfNotExist=true 
    username: <your sql server username>
    password: <your sql server password>
    driver-class-name: com.mysql.cj.jdbc.Driver
   ```
 * File Location: VProject/Core/Movie-service/movie-service-api/src/main/resources/application.properties	
    * Configuration:
    ```
    datasource :url: jdbc:mysql://localhost:3306/movie?createDatabaseIfNotExist=true 
    username: <your sql server username>
    password: <your sql server password>
    driver-class-name: com.mysql.cj.jdbc.Driver
   ```
 * File Location: VProject/Core/Rating-Service/rating-service-api/src/main/resources/application.properties
    * Configuration:
    ```
    datasource :url: jdbc:mysql://localhost:3306/rating?createDatabaseIfNotExist=true 
    username: <your sql server username>
    password: <your sql server password>
    driver-class-name: com.mysql.cj.jdbc.Driver
    ```

## NOTE
* For ReactJs Integrated project please follow this link:
http://github.com - automatic!
[GitHub](http://github.com)

## CONTRIBUTORS
@madhavtib, @birajghosh6





