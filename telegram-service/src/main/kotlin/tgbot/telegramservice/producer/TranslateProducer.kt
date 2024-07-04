package tgbot.telegramservice.producer

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import tgbot.telegramservice.config.KafkaProperties
import tgbot.telegramservice.model.TranslateRequestEvent


@Service("translate")
class TranslateProducer(
    val translateRequestKafkaTemplate: KafkaTemplate<String, TranslateRequestEvent>,
    val kafkaProperties: KafkaProperties
) : Producer {

    val log = LoggerFactory.getLogger(this::class.java)

    override fun <T : BotEvent> send(event: T): SendMessage? {
        if (event !is TranslateRequestEvent) return null
        val result = translateRequestKafkaTemplate.send(kafkaProperties.topics.translateRequest, event.chatId, event).get()
        log.info("Request: $event")
        log.info("Topic: ${result.recordMetadata.topic()}")
        log.info("Partition: ${result.recordMetadata.partition()}")
        log.info("Offset: ${result.recordMetadata.offset()}")
        return null
    }
}