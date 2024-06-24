package tgbot.telegramservice.handler.util

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import tgbot.telegramservice.handler.BotCommandHandler
import tgbot.telegramservice.keyboard.startMessageKeyboard
import tgbot.telegramservice.service.chatStarted
import tgbot.telegramservice.service.lastMainCommand
import tgbot.telegramservice.service.lastTranslateCommand

fun startCommandHandler(chatId: String): SendMessage {
    startChat(chatId)
    addLastMainCommand(chatId, BotCommandHandler.MainCommand.START)
    return startMessageKeyboard(chatId)
}

fun stopCommandHandler(chatId: String): SendMessage {
    stopChat(chatId)
    addLastMainCommand(chatId, BotCommandHandler.MainCommand.STOP)
    return SendMessage(chatId, "Goodbye")
}

private fun startChat(chatId: String) {
    chatStarted[chatId] = true
}

private fun stopChat(chatId: String) {
    chatStarted[chatId] = false
}

fun isChatStarted(chatId: String): Boolean = chatStarted[chatId] ?: false

fun isLastCommandTranslate(chatId: String): Boolean =
    getLastMainCommand(chatId) == BotCommandHandler.MainCommand.TRANSLATE

fun chatNotStartedMsg(chatId: String): SendMessage = SendMessage(chatId, "Запустите чат командой /start")

fun translatorNotSelectedMsg(chatId: String): SendMessage = SendMessage(chatId, "Сначала выберите переводчик!")

fun addLastMainCommand(chatId: String, lastComm: BotCommandHandler.MainCommand) {
    lastMainCommand[chatId] = lastComm
}

fun getLastMainCommand(chatId: String): BotCommandHandler.MainCommand? = lastMainCommand[chatId]

fun addLastTranslateCommand(chatId: String, lastComm: BotCommandHandler.TranslateCommand) {
    lastTranslateCommand[chatId] = lastComm
}

fun getLastTranslateCommand(chatId: String): BotCommandHandler.TranslateCommand? = lastTranslateCommand[chatId]