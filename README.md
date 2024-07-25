# Telegram bot
Microservice application telegram bot.    
The following functionality has been implemented: 
- translator ru-en / en-ru   
- weather forecast

It is also planned to add other functionality. Architecturally, the functionality is divided into microservices exchanging messages through the Kafka cluster.

All services are launched in Docker using docker compose.


### Build project
```
gradle clean build
``` 

### Run project
```
docker-compose up -d
``` 

Stack:

- Kotlin
- Spring Boot 3
- Spring Cloud Config Server
- Redis
- Kafka
