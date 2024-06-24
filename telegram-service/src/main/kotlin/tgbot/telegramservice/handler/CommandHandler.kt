package tgbot.telegramservice.handler

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import tgbot.telegramservice.handler.util.*


object BotCommandHandler {

    fun handle(msg: Message): SendMessage {
        val chatId = msg.chatId.toString()
        return when (msg.text) {
            MainCommand.START.value -> startCommandHandler(chatId)
            MainCommand.STOP.value -> stopCommandHandler(chatId)
            else -> SendMessage(chatId, "Неизвестная команда!")
        }
    }

    enum class MainCommand(val value: String) {
        START("/start"),
        STOP("/stop"),
        TRANSLATE("/translate")
    }

    enum class TranslateCommand(val value: String, val direction: String) {
        RU_EN("/ru-en", "ru|en"),
        EN_RU("/en-ru", "en|ru")
    }

}