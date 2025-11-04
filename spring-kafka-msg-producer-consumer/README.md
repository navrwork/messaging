# Spring Kafka Message Producer Consumer Project

## Reference
* [Run a Kafka broker locally with Docker | confluent.io](https://developer.confluent.io/confluent-tutorials/kafka-on-docker/)
* [Spring Kafka Producer Example |
  The Java Tech Learning | YouTube](https://www.youtube.com/watch?v=cXBM_xVAnRg&list=PLOcnwq0FwVrQd7fxM64p6aR4mIxG3ZdYw&index=8)
* [apache/kafka | hub.docker.com](https://hub.docker.com/r/apache/kafka/)

## Tech Stack
* Spring Boot 3.x
* Apache Kafka
* Docker Desktop | Docker Engine

## Kafka Producer

### Commands to run Kafka broker locally
* Install [Docker Desktop](https://www.docker.com/products/docker-desktop/) on windows.
* Use [Docker Compose](src/main/resources/docker-compose.yml) to create your Kafka container.
* Start the Docker Desktop app. Ensure that the Desktop Engine is in Running state. 
* Open command prompt and navigate to $PROJECT/src/main/resources/ folder. This is where the docker-compose.yml file is present.
* Execute the below command to run the docker container in detached mode:
```text
docker compose up -d
```
* Execute below command to open a command terminal on Kafka container. This opens a new terminal on the Kafka container (not on the host/Windows). 
```text
docker exec -it -w /opt/kafka/bin broker sh
```
* Execute below command to create a topic (note: you're still within Kafka container terminal).
```shell
./kafka-topics.sh --create --topic my-topic --bootstrap-server broker:29092
```
Take note of the --bootstrap-server flag. Because you're connecting to Kafka inside the container, you use broker:29092 for the host:port. If you were to use a client outside the container to connect to Kafka, a producer application running on your laptop for example, you'd use localhost:9092 instead.
You should see the below message after successful topic creation.
```
Created topic my-topic.
```
* You may now exit the container terminal.
```shell
exit
```
* Move ahead with Kafka Producer related tasks. 

### Send Messages to Kafka
* Run the _SpringKafkaApplication_ Spring boot app from your desired IDE. 
* You should see below message in the console:
```text
Started SpringKafkaApplication in ... seconds
```
* Open a Rest client (like Postman) and invoke the below url:
```text
http://localhost:9191/producer-app/publish/HelloWorld1
```
* The service should send a new message to Kafka. You should see below message in Spring app console:
```text
Sent message=[HelloWorld1] with offset=[0]
```

### Shut Down Kafka Container
* You can stop / shutdown the Kafka container either via Docker Desktop or using the below command:
```text
docker compose down -v
```
