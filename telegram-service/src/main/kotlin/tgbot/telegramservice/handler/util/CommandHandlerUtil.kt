package tgbot.telegramservice.handler.util

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import tgbot.telegramservice.entity.User
import tgbot.telegramservice.handler.AccountCommand
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
        return startMessageKeyboard(user)
    }

    fun stopCommandHandler(user: User): SendMessage {
        stopChat(user)
        addLastMainCommand(user, MainCommand.STOP)
        return SendMessage(user.chatId, "Goodbye")
    }

    private fun startChat(user: User) {
        user.chatStarted = true
        userService.saveUser(user)
    }

    private fun stopChat(user: User) {
        user.chatStarted = false
        userService.saveUser(user)
    }

    fun isChatStarted(user: User): Boolean = user.chatStarted

    fun isLastServiceCommandTranslate(user: User): Boolean = user.lastServiceCommand == ServiceCommand.TRANSLATE

    fun chatNotStartedMsg(user: User): SendMessage = SendMessage(user.chatId, "Запустите чат командой /start")

    fun translatorNotSelectedMsg(user: User): SendMessage = SendMessage(user.chatId, "Сначала выберите переводчик!")

    fun addLastMainCommand(user: User, lastComm: MainCommand) {
        user.lastMainCommand = lastComm
        userService.saveUser(user)
    }

    fun addLastServiceCommand(user: User, lastComm: ServiceCommand) {
        user.lastServiceCommand = lastComm
        userService.saveUser(user)
    }

    fun getLastServiceCommand(user: User): ServiceCommand? = user.lastServiceCommand

    fun addLastTranslateCommand(user: User, lastComm: TranslateCommand) {
        user.lastTranslateCommand = lastComm
        userService.saveUser(user)
    }

    fun getLastTranslateCommand(user: User): TranslateCommand? = user.lastTranslateCommand

    fun getLastAccountCommand(user: User): AccountCommand = user.lastAccountCommand

    fun addLastAccountCommand(user: User, lastComm: AccountCommand) {
        user.lastAccountCommand = lastComm
        userService.saveUser(user)
    }
}
