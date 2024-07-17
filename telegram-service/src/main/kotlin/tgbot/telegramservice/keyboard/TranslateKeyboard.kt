package tgbot.telegramservice.keyboard

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import tgbot.telegramservice.entity.User


fun translateKeyboard(user: User): SendMessage {
    val message = SendMessage(user.chatId, "С какого языка на какой перевести")
    val keyboard = InlineKeyboardMarkup().apply {
        val buttonRow1 = listOf(ruToEnBtn, enToRuBtn)
        val buttonRow2 = listOf(toMainMenuBtn)
        keyboard = listOf(buttonRow1, buttonRow2)
    }
    message.replyMarkup = keyboard
    return message
}

// TODO: need?
fun translateResponseKeyboard(chatId: String, response: String): SendMessage {
    val message = SendMessage(chatId, response)
    val keyboard = InlineKeyboardMarkup().apply {
        val buttonRow1 = listOf(toMainMenuBtn)
        keyboard = listOf(buttonRow1)
    }
    message.replyMarkup = keyboard
    return message
}