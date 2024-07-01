package tgbot.telegramservice.consumer

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import tgbot.telegramservice.model.TranslateResponse
import tgbot.telegramservice.model.TranslateResponseEvent
import tgbot.telegramservice.service.TelegramBot


@Component
class TranslateResponseEventHandler(
    val bot: TelegramBot
) {

    @KafkaListener(
        topics = ["translate-response-event-topic"],
        containerFactory = "translateResponseListenerContainerFactory"
    )
    fun handler(translateResponse: TranslateResponseEvent) {
        bot.sendResponse(TranslateResponse(translateResponse.chatId, translateResponse.response))
    }

}
