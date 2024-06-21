package tgbot.telegramservice.producer

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import tgbot.telegramservice.model.TranslateRequestEvent


@Service("translate")
class TranslateProducer(
    val kafkaTemplate: KafkaTemplate<String, TranslateRequestEvent>,
    @Value("\${topic.translate.request.name}")
    val translateRequestTopicName: String
) : Producer {

    val log = LoggerFactory.getLogger(this::class.java)

    override fun <T : BotEvent> send(event: T) {
        if (event !is TranslateRequestEvent) return
        val result = kafkaTemplate.send(translateRequestTopicName, event.chatId, event).get()
        log.info("Request: $event")
        log.info("Topic: ${result.recordMetadata.topic()}")
        log.info("Partition: ${result.recordMetadata.partition()}")
        log.info("Offset: ${result.recordMetadata.offset()}")
    }
}