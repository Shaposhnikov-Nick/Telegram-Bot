package tgbot.telegramservice.keyboard

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import tgbot.telegramservice.handler.Callback


fun translateKeyboard(chatId: String): SendMessage {
    val message = SendMessage(chatId, "С какого языка на какой перевести")
    val keyboard = InlineKeyboardMarkup()
    val ruToEnBtn = InlineKeyboardButtonWrapper("RU -> EN",  callbackData = Callback.RU_EN.value)
    val enToRuBtn = InlineKeyboardButtonWrapper("EN -> RU",  callbackData = Callback.EN_RU.value)
    val buttonRow1 = listOf(ruToEnBtn, enToRuBtn)
    val buttonRow2 = listOf(toMainMenuBtn)
    keyboard.keyboard = listOf(buttonRow1, buttonRow2)
    message.replyMarkup = keyboard
    return message
}

fun translateResponseKeyboard(chatId: String, response: String): SendMessage {
    val message = SendMessage(chatId, response)
    val keyboard = InlineKeyboardMarkup()
    val buttonRow1 = listOf(toMainMenuBtn)
    keyboard.keyboard = listOf(buttonRow1)
    message.replyMarkup = keyboard
    return message
}