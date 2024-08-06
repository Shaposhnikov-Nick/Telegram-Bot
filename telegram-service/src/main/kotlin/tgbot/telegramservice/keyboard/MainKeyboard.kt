package tgbot.telegramservice.keyboard

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import tgbot.telegramservice.entity.User


fun startMessageKeyboard(user: User): SendMessage {
    val message = SendMessage(user.chatId, "Узнать, что я могу")
    val keyboard = InlineKeyboardMarkup().apply {
        val buttonRow1 = listOf(toMainMenuBtn)
        keyboard = listOf(buttonRow1)
    }
    message.replyMarkup = keyboard
    return message
}

fun mainMenuKeyboard(user: User): SendMessage {
    val message = SendMessage(user.chatId, "Что бот умеет")
    val keyboard = InlineKeyboardMarkup().apply {
        val buttonRow1 = listOf(translateBtn, weatherBtn)
        val buttonRow2 = listOf(accountBtn, otherBtn)
        keyboard = listOf(buttonRow1, buttonRow2)
    }
    message.replyMarkup = keyboard
    return message
}