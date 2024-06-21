package tgbot.translateservice.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import tgbot.translateservice.model.TranslateRequestEvent

@Component
class TranslateRequestEventHandler(
    val translateService: TranslateService
) {

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)
        const val properties = "spring.json.value.default.type=tgbot.translateservice.model.TranslateRequestEvent"
    }

    @KafkaListener(topics = ["translate-request-event-topic"], groupId = "translate", properties = [properties])
    suspend fun handler(translateRequest: TranslateRequestEvent) {
        log.info("Request: $translateRequest")
        val result = translateService.translate(translateRequest)
        log.info("Result: $result")
    }

}