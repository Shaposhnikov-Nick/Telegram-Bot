package tgbot.telegramservice.handler.util

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import tgbot.telegramservice.handler.BotCommandHandler
import tgbot.telegramservice.keyboard.mainMenuKeyboard
import tgbot.telegramservice.keyboard.translateKeyboard


fun mainMenuCallbackHandler(chatId: String): SendMessage{
    return mainMenuKeyboard(chatId)
}

fun translateCallbackHandler(chatId: String): SendMessage {
    return if (isChatStarted(chatId)) {
        addLastMainCommand(chatId, BotCommandHandler.MainCommand.TRANSLATE)
        translateKeyboard(chatId)
    } else chatNotStartedMsg(chatId)
}

fun enRuTranslateCallbackHandler(chatId: String): SendMessage {
    return if (!isChatStarted(chatId)) {
        chatNotStartedMsg(chatId)
    } else if (!isLastCommandTranslate(chatId)) {
        translatorNotSelectedMsg(chatId)
    } else {
        addLastTranslateCommand(chatId, BotCommandHandler.TranslateCommand.EN_RU)
        SendMessage(chatId, "Введите слово или фразу на английском")
    }
}

fun ruEnTranslateCallbackHandler(chatId: String): SendMessage {
    return if (!isChatStarted(chatId)) {
        chatNotStartedMsg(chatId)
    } else if (!isLastCommandTranslate(chatId)) {
        translatorNotSelectedMsg(chatId)
    } else {
        addLastTranslateCommand(chatId, BotCommandHandler.TranslateCommand.RU_EN)
        SendMessage(chatId, "Введите слово или фразу по русски")
    }
}