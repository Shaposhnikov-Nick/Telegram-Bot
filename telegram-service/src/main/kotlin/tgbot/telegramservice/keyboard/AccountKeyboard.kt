package tgbot.telegramservice.keyboard

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import tgbot.telegramservice.entity.User


fun accountKeyboard(user: User): SendMessage {
    val message = SendMessage(user.chatId, "Получить или добавить данные о себе\n")
    val keyboard = InlineKeyboardMarkup().apply {
        val buttonRow1 = listOf(getAccountBtn, saveAccountBtn)
        val buttonRow2 = listOf(toMainMenuBtn)
        keyboard = listOf(buttonRow1, buttonRow2)
    }
    message.replyMarkup = keyboard
    return message
}