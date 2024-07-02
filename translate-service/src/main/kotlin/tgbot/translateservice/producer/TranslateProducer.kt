package tgbot.translateservice.producer

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import tgbot.translateservice.config.KafkaProperties
import tgbot.translateservice.model.TranslateResponseEvent


@Service
class TranslateProducer(
    val translateResponseKafkaTemplate: KafkaTemplate<String, TranslateResponseEvent>,
    val kafkaProperties: KafkaProperties
) : Producer {

    val log = LoggerFactory.getLogger(this::class.java)

    override suspend fun <T : BotEvent> send(event: T) {
        if (event !is TranslateResponseEvent) return
        val result = translateResponseKafkaTemplate.send(kafkaProperties.topics.translateResponse, event.chatId, event).get()
        log.info("Request: $event")
        log.info("Topic: ${result.recordMetadata.topic()}")
        log.info("Partition: ${result.recordMetadata.partition()}")
        log.info("Offset: ${result.recordMetadata.offset()}")
    }
}