# Telegram bot
Microservice application telegram bot.    
The following functionality has been implemented: 
- translator ru-en / en-ru   

It is also planned to add other functionality. Architecturally, the functionality is divided into microservices exchanging messages through the Kafka cluster



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
- Redis
- Kafka
