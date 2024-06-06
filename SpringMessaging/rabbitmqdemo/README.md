# Spring Boot And Rabbit MQ - Demo App

## Rabbit MQ

### Reference
* **[ActiveMQ vs RabbitMQ | cloudamqp.com](https://www.cloudamqp.com/blog/activemq-vs-rabbitmq-an-indepth-comparison.html)**
* **[ActiveMQ vs RabbitMQ | stackshare.io](https://stackshare.io/stackups/activemq-vs-rabbitmq)**

### RabbitMQ Setup
* Download RabbitMQ image from Docker hub
* Use Docker Desktop and run the image. Make sure to map 5672 and 15672 ports when you run the image.
* Reference command to run the image from CLI: 
  * docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3.13.2-management


### RabbitMQ Management Console
* Console: http://localhost:15672/
* Create a new queue with same name as the one configured in application.properties file.
* Create a new exchange as well
* Create a binding using the routing key

## REST API
* Start SpringBoot app.
* To produce a message, use any RestClient and invoke appropriate api
* MessageConsumer will automatically consume the message, once produced.

### Sample API Request and Response
#### produceMessage API
* Request (HTTP GET): 
  * curl --location 'http://localhost:8080/api/v1/produceMessage/aaa'
* Response:
  * Message sent -> aaa
#### produceJsonMessage API
* Request (HTTP POST):
  * curl --location 'http://localhost:8080/api/v1/produceJsonMessage' \
    --header 'Content-Type: application/json' \
    --data '{
    "userId": 1001,
    "firstName": "John",
    "lastName": "Spencer",
    "birthDate": "2000-01-01"
    }'
* Response:
  * {
    "statusCode": 200,
    "message": "produceJsonMessage successful."
    } 