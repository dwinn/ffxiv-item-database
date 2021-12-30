# FFXIV Item Database

Small work in progress project to demonstrate a Spring Boot microservice with Hibernate.

The project uses a MySQL database, Liquibase, Swagger and Github Actions for CI/CD.

## Running Locally
To run, first build the project: `./gradlew clean build`
Then start up Docker: `docker-compose up --build`
Access Swagger: `http://localhost:8080/swagger-ui/`

Sample item ID for trying out the API: 1675
