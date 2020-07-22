# Make Magic Api

Make Magic is a REST API for managing character registrations in the Harry Potter saga, a basic CRUD. This project is available on heroku through the endpoints:
- [https://make-magic-api.herokuapp.com/swagger-ui.html](https://make-magic-api.herokuapp.com/swagger-ui.html) for Swagger documentation
- [https://make-magic-api.herokuapp.com/api/characters](https://make-magic-api.herokuapp.com/api/characters) for registered characters list or register new character
    - This endpoint accept two query string parameters to filter **?house={house hash code}** and **patronus={patronus name}**
- [https://make-magic-api.herokuapp.com/api/characters/{id}](https://make-magic-api.herokuapp.com/api/characters/{id}) for get, update or delete a specific character

# P.S
Being hosted in heroku, the first call may take a while. Heroku works with a demand system, if the application takes too long without receiving requests, the first will serve to start the server.

If you prefer to run locally, follow the steps below

# How to run
  - Open the application.properties file at /src/main/resources
  - Change all wild words for your database credentials (${DATABASE_URL}) for example
  - Run the command to build container `docker build --build-arg JAR_FILE=build/libs/make-magic-docker-0.1.0.jar -t make-magic-api .`
  - Run the command to run container `docker run -p 8080:8080 make-magic-api`
  
# Technologies
  - [Java 8](https://docs.oracle.com/en/java/).
  - [Spring Boot Framework](https://spring.io/)
  - [Potter API](https://www.potterapi.com/)
  - [PostgreSQL](https://www.postgresql.org/)


# Architecture
 - Service Application layer: where the controllers were developed, it is responsible for receiving and forwarding requests
 - Domain Service layer: core of validations and business logic before persist on database.
 - Domain Model layer: responsible for describing all application models that will later be mapped to the database.
 - Infrastructure layer: can be divided in one more layer
    - Data layer: responsible persistence with the database, using SqlAlchemy as ORM
 - Tests layer: responsible for testing the application services and domain services.

![Architecture](https://miro.medium.com/max/732/1*P4zm6LF9l0nRmyN2iqjgHQ.png)