package tgbot.telegramservice.consumer

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import tgbot.telegramservice.model.ServiceResponse
import tgbot.telegramservice.model.TranslateResponseEvent
import tgbot.telegramservice.service.TelegramBot


@Component
class TranslateResponseEventHandler(
    val bot: TelegramBot
) {

    @KafkaListener(
        topics = ["#{kafkaProperties.topics.translateResponse}"],
        containerFactory = "translateResponseListenerContainerFactory"
    )
    fun handler(translateResponse: TranslateResponseEvent) {
        bot.sendResponse(ServiceResponse(translateResponse.chatId, translateResponse))
    }

}
