
# Job app

A job app with 3 entities: company, job and review.

## Run locally

Clone the project
```bash
  git clone https://github.com/spancavil/JavaJobApp.git
```
Position yourself in the monolithic branch
```bash
  git checkout monolithic
```
> **Note**: Docker must be installed in order to continue.

Run the application from IntelliJ

Run all the containers using docker-compose
```bash
docker compose up
```

## Tech stacks

* Java 22.
* Spring Boot.
* Actuator => for logging metrics and server info.
* PostgreSQL.
* Docker and docker compose.