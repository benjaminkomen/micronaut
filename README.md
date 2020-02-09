# Micronaut GraalVM

This repository tries to get Micronaut with GraalVM running on Cloud Run.

## Instructions
### Run locally
To run this project, compile it via
`./mvnw clean compile` and run directly with Java via
`java -jar target/micronaut-1.0.0.jar`.
                                                                          
Alternatively you can build the Docker image locally
with `docker build -t micronaut .` and then run it with
`docker run -it -e PORT=8080 -p 8080:8080 micronaut` and view it again
via your browser on http://localhost:8080.

### Run in Google Cloud Run
