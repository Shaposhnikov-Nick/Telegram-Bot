package tgbot.telegramservice.model

import tgbot.telegramservice.producer.BotEvent

data class TranslateRequestEvent(
    val chatId: String,
    val query: String,
    val langPair: String
): BotEvent