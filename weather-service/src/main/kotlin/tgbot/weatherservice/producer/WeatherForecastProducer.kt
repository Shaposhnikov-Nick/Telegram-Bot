package tgbot.weatherservice.producer

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import tgbot.weatherservice.config.KafkaProperties
import tgbot.weatherservice.model.WeatherResponseEvent


@Service
class WeatherForecastProducer(
    val weatherForecastResponseKafkaTemplate: KafkaTemplate<String, WeatherResponseEvent>,
    val kafkaProperties: KafkaProperties
) : Producer {

    val log = LoggerFactory.getLogger(this::class.java)

    override suspend fun <T : BotEvent> send(event: T) {
        if (event !is WeatherResponseEvent) return
        val result = weatherForecastResponseKafkaTemplate.send(kafkaProperties.topics.weatherResponse, event.chatId, event).get()
        log.info("Request: $event")
        log.info("Topic: ${result.recordMetadata.topic()}")
        log.info("Partition: ${result.recordMetadata.partition()}")
        log.info("Offset: ${result.recordMetadata.offset()}")
    }
}