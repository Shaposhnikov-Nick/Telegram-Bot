package tgbot.translateservice.consumer

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import tgbot.translateservice.model.TranslateRequestEvent
import tgbot.translateservice.model.TranslateResponseEvent
import tgbot.translateservice.producer.Producer
import tgbot.translateservice.service.TranslateService

@Component
class TranslateRequestEventHandler(
    val translateService: TranslateService,
    val translateProducer: Producer
) {

    val log = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(
        topics = ["translate-request-event-topic"],
        containerFactory = "translateRequestListenerContainerFactory"
    )
    suspend fun handler(translateRequest: TranslateRequestEvent) {
        log.info("Request: $translateRequest")
        val result = translateService.translate(translateRequest)
        log.info("Result: $result")
        translateProducer.send(TranslateResponseEvent(translateRequest.chatId, result.responseData.translatedText))
    }

}