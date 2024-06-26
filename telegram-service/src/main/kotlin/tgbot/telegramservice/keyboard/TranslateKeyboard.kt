package tgbot.telegramservice.keyboard

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup


fun translateKeyboard(chatId: String): SendMessage {
    val message = SendMessage(chatId, "С какого языка на какой перевести")
    val keyboard = InlineKeyboardMarkup().apply {
        val buttonRow1 = listOf(ruToEnBtn, enToRuBtn)
        val buttonRow2 = listOf(toMainMenuBtn)
        keyboard = listOf(buttonRow1, buttonRow2)
    }
    message.replyMarkup = keyboard
    return message
}

fun translateResponseKeyboard(chatId: String, response: String): SendMessage {
    val message = SendMessage(chatId, response)
    val keyboard = InlineKeyboardMarkup().apply {
        val buttonRow1 = listOf(toMainMenuBtn)
        keyboard = listOf(buttonRow1)
    }
    message.replyMarkup = keyboard
    return message
}