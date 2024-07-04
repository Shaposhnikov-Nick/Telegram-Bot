package tgbot.telegramservice.handler.util

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import tgbot.telegramservice.handler.MainCommand
import tgbot.telegramservice.handler.ServiceCommand
import tgbot.telegramservice.handler.TranslateCommand
import tgbot.telegramservice.keyboard.startMessageKeyboard
import tgbot.telegramservice.service.chatStarted
import tgbot.telegramservice.service.lastMainCommand
import tgbot.telegramservice.service.lastServiceCommand
import tgbot.telegramservice.service.lastTranslateCommand

fun startCommandHandler(chatId: String): SendMessage {
    startChat(chatId)
    addLastMainCommand(chatId, MainCommand.START)
    return startMessageKeyboard(chatId)
}

fun stopCommandHandler(chatId: String): SendMessage {
    stopChat(chatId)
    addLastMainCommand(chatId, MainCommand.STOP)
    return SendMessage(chatId, "Goodbye")
}

private fun startChat(chatId: String) {
    chatStarted[chatId] = true
}

private fun stopChat(chatId: String) {
    chatStarted[chatId] = false
}

fun isChatStarted(chatId: String): Boolean = chatStarted[chatId] ?: false

fun isLastServiceCommandTranslate(chatId: String): Boolean =
    getLastServiceCommand(chatId) == ServiceCommand.TRANSLATE

fun chatNotStartedMsg(chatId: String): SendMessage = SendMessage(chatId, "Запустите чат командой /start")

fun translatorNotSelectedMsg(chatId: String): SendMessage = SendMessage(chatId, "Сначала выберите переводчик!")

fun addLastMainCommand(chatId: String, lastComm: MainCommand) {
    lastMainCommand[chatId] = lastComm
}

fun addLastServiceCommand(chatId: String, lastComm: ServiceCommand) {
    lastServiceCommand[chatId] = lastComm
}

fun getLastMainCommand(chatId: String): MainCommand? = lastMainCommand[chatId]

fun getLastServiceCommand(chatId: String): ServiceCommand? = lastServiceCommand[chatId]

fun addLastTranslateCommand(chatId: String, lastComm: TranslateCommand) {
    lastTranslateCommand[chatId] = lastComm
}

fun getLastTranslateCommand(chatId: String): TranslateCommand? = lastTranslateCommand[chatId]