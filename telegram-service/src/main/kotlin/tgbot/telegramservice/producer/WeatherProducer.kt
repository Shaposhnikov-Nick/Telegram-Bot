package tgbot.telegramservice.producer

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import tgbot.telegramservice.config.KafkaProperties
import tgbot.telegramservice.model.WeatherRequestEvent


@Service("weather")
class WeatherProducer(
    val weatherRequestKafkaTemplate: KafkaTemplate<String, WeatherRequestEvent>,
    val kafkaProperties: KafkaProperties
) : Producer {

    val log = LoggerFactory.getLogger(this::class.java)

    override fun <T : BotEvent> send(event: T): SendMessage? {
        if (event !is WeatherRequestEvent) return null
        val result = weatherRequestKafkaTemplate.send(kafkaProperties.topics.weatherRequest, event.chatId, event).get()
        log.info("Request: $event")
        log.info("Topic: ${result.recordMetadata.topic()}")
        log.info("Partition: ${result.recordMetadata.partition()}")
        log.info("Offset: ${result.recordMetadata.offset()}")
        return null
    }
}