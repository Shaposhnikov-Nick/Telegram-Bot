package tgbot.telegramservice.handler.util

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import tgbot.telegramservice.entity.User
import tgbot.telegramservice.handler.MainCommand
import tgbot.telegramservice.handler.ServiceCommand
import tgbot.telegramservice.handler.TranslateCommand
import tgbot.telegramservice.keyboard.startMessageKeyboard
import tgbot.telegramservice.service.*


@Component
class CommandHandlerUtil(
    val userService: UserService
) {

    fun startCommandHandler(user: User): SendMessage {
        startChat(user)
        addLastMainCommand(user, MainCommand.START)
        return startMessageKeyboard(user.id)
    }

    fun stopCommandHandler(user: User): SendMessage {
        stopChat(user)
        addLastMainCommand(user, MainCommand.STOP)
        return SendMessage(user.id, "Goodbye")
    }

    private fun startChat(user: User) {
        user.chatStarted = true
        userService.saveUser(user)
    }

    private fun stopChat(user: User) {
        user.chatStarted = false
        userService.saveUser(user)
    }

    fun isChatStarted(chatId: String): Boolean = chatStarted[chatId] ?: false

    fun isLastServiceCommandTranslate(chatId: String): Boolean =
        getLastServiceCommand(chatId) == ServiceCommand.TRANSLATE

    fun chatNotStartedMsg(chatId: String): SendMessage = SendMessage(chatId, "Запустите чат командой /start")

    fun translatorNotSelectedMsg(chatId: String): SendMessage = SendMessage(chatId, "Сначала выберите переводчик!")

    fun addLastMainCommand(user: User, lastComm: MainCommand) {
        user.lastMainCommand = lastComm
        userService.saveUser(user)
    }

    fun addLastServiceCommand(chatId: String, lastComm: ServiceCommand) {
        lastServiceCommand[chatId] = lastComm
    }

    fun getLastServiceCommand(chatId: String): ServiceCommand? = lastServiceCommand[chatId]

    fun addLastTranslateCommand(chatId: String, lastComm: TranslateCommand) {
        lastTranslateCommand[chatId] = lastComm
    }

    fun getLastTranslateCommand(chatId: String): TranslateCommand? = lastTranslateCommand[chatId]
}
