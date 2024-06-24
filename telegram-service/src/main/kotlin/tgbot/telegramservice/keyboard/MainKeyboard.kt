package tgbot.telegramservice.keyboard

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import tgbot.telegramservice.handler.Callback


val toMainMenuBtn = InlineKeyboardButtonWrapper("В главное меню", callbackData = Callback.MAIN_MENU.value)

fun startMessageKeyboard(chatId: String): SendMessage {
    val message = SendMessage(chatId, "Узнать, что я могу")
    val keyboard = InlineKeyboardMarkup()
    val buttonRow1 = listOf(toMainMenuBtn)
    keyboard.keyboard = listOf(buttonRow1)
    message.replyMarkup = keyboard
    return message
}

fun mainMenuKeyboard(chatId: String): SendMessage {
    val message = SendMessage(chatId, "Что бот умеет")
    val keyboard = InlineKeyboardMarkup()
    val translateBtn = InlineKeyboardButtonWrapper("Переводчик", callbackData = Callback.TRANSLATE.value)
    val otherBtn = InlineKeyboardButtonWrapper("Что-то другое...", callbackData = "other")
    val buttonRow1 = listOf(translateBtn, otherBtn)
    keyboard.keyboard = listOf(buttonRow1)
    message.replyMarkup = keyboard
    return message
}