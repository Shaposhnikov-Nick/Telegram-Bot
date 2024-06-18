package tgbot.telegramservice.handler.util

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import tgbot.telegramservice.handler.BotCommandHandler
import tgbot.telegramservice.service.chatStarted
import tgbot.telegramservice.service.lastMainCommand
import tgbot.telegramservice.service.lastTranslateCommand

fun startCommandHandler(chatId: String): SendMessage {
    startChat(chatId)
    addLastMainCommand(chatId, BotCommandHandler.MainCommand.START)
    return SendMessage(chatId, "Hello")
}

fun stopCommandHandler(chatId: String): SendMessage {
    stopChat(chatId)
    addLastMainCommand(chatId, BotCommandHandler.MainCommand.STOP)
    return SendMessage(chatId, "Goodbye")
}

fun translateCommandHandler(chatId: String): SendMessage {
    return if (isChatStarted(chatId)) {
        addLastMainCommand(chatId, BotCommandHandler.MainCommand.TRANSLATE)
        SendMessage(chatId, "С какого языка на какой перевести")
    } else chatNotStartedMsg(chatId)
}

fun translateEnRuCommandHandler(chatId: String): SendMessage {
    return if (!isChatStarted(chatId)) {
        chatNotStartedMsg(chatId)
    } else if (!isLastCommandTranslate(chatId)) {
        translatorNotSelectedMsg(chatId)
    } else {
        addLastTranslateCommand(chatId, BotCommandHandler.TranslateCommand.EN_RU)
        SendMessage(chatId, "Введите слово или фразу на английском")
    }
}

fun translateRuEnCommandHandler(chatId: String): SendMessage {
    return if (!isChatStarted(chatId)) {
        chatNotStartedMsg(chatId)
    } else if (!isLastCommandTranslate(chatId)) {
        translatorNotSelectedMsg(chatId)
    } else {
        addLastTranslateCommand(chatId, BotCommandHandler.TranslateCommand.RU_EN)
        SendMessage(chatId, "Введите слово или фразу по русски")
    }
}

private fun startChat(chatId: String) {
    chatStarted[chatId] = true
}

private fun stopChat(chatId: String) {
    chatStarted[chatId] = false
}

fun isChatStarted(chatId: String): Boolean = chatStarted[chatId] ?: false
private fun isLastCommandTranslate(chatId: String): Boolean =
    getLastMainCommand(chatId) == BotCommandHandler.MainCommand.TRANSLATE

fun chatNotStartedMsg(chatId: String): SendMessage = SendMessage(chatId, "Chat not started!")

private fun translatorNotSelectedMsg(chatId: String): SendMessage = SendMessage(chatId, "Сначала выберите переводчик!")

private fun addLastMainCommand(chatId: String, lastComm: BotCommandHandler.MainCommand) {
    lastMainCommand[chatId] = lastComm
}

fun getLastMainCommand(chatId: String): BotCommandHandler.MainCommand? = lastMainCommand[chatId]

private fun addLastTranslateCommand(chatId: String, lastComm: BotCommandHandler.TranslateCommand) {
    lastTranslateCommand[chatId] = lastComm
}

private fun getLastTranslateCommand(chatId: String): BotCommandHandler.TranslateCommand? = lastTranslateCommand[chatId]