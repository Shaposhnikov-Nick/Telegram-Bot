package tgbot.telegramservice.keyboard

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup


fun startMessageKeyboard(chatId: String): SendMessage {
    val message = SendMessage(chatId, "Узнать, что я могу")
    val keyboard = InlineKeyboardMarkup().apply {
        val buttonRow1 = listOf(toMainMenuBtn)
        keyboard = listOf(buttonRow1)
    }
    message.replyMarkup = keyboard
    return message
}

fun mainMenuKeyboard(chatId: String): SendMessage {
    val message = SendMessage(chatId, "Что бот умеет")
    val keyboard = InlineKeyboardMarkup().apply {
        val buttonRow1 = listOf(translateBtn, otherBtn)
        keyboard = listOf(buttonRow1)
    }
    message.replyMarkup = keyboard
    return message
}