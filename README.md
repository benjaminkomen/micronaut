# Micronaut GraalVM

This is a simple Micronaut webserver compiled with GraalVM running on Cloud Run. It exposes
2 endpoints:
- /api/hello
- /api/fb

You can see it live in action [here](https://micronaut-ymlwsbp6oq-ew.a.run.app/api/hello).

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
You can do the following 2 command lines one after the other:
```bash
gcloud builds submit --tag gcr.io/micronaut-cloudrun/micronaut --machine-type=n1-highcpu-32 --timeout 2400s .
gcloud run deploy --image gcr.io/micronaut-cloudrun/micronaut --platform managed --region europe-west1 --memory 1Gi
```

Or you can make use of the [cloud build](./cloudbuild.yaml) file to automate a trigger from github.
