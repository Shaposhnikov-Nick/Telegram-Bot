package tgbot.telegramservice.model

import tgbot.telegramservice.producer.BotEvent

data class TranslateRequestEvent(
    val chatId: String,
    val query: String,
    val langPair: String
): BotEvent

data class TranslateResponseEvent(
    val chatId: String,
    val response: String

): Event {
    override fun getMessageBody(): String {
        return response
    }
}