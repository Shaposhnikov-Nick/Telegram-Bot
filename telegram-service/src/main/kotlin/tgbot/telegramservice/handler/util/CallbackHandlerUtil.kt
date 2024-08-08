package tgbot.telegramservice.handler.util

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import tgbot.telegramservice.entity.User
import tgbot.telegramservice.handler.AccountCommand
import tgbot.telegramservice.handler.ServiceCommand
import tgbot.telegramservice.handler.TranslateCommand
import tgbot.telegramservice.keyboard.accountKeyboard
import tgbot.telegramservice.keyboard.mainMenuKeyboard
import tgbot.telegramservice.keyboard.translateKeyboard
import tgbot.telegramservice.model.AccountRequest
import tgbot.telegramservice.producer.AccountService


@Component
class CallbackHandlerUtil(
    val commandUtil: CommandHandlerUtil,
    val accountService: AccountService
) {
    fun mainMenuCallbackHandler(user: User): SendMessage {
        return mainMenuKeyboard(user)
    }

    fun translateCallbackHandler(user: User): SendMessage {
        return if (commandUtil.isChatStarted(user)) {
            commandUtil.addLastServiceCommand(user, ServiceCommand.TRANSLATE)
            translateKeyboard(user)
        } else commandUtil.chatNotStartedMsg(user)
    }

    fun enRuTranslateCallbackHandler(user: User): SendMessage {
        return if (!commandUtil.isChatStarted(user)) {
            commandUtil.chatNotStartedMsg(user)
        } else if (!commandUtil.isLastServiceCommandTranslate(user)) {
            commandUtil.translatorNotSelectedMsg(user)
        } else {
            commandUtil.addLastTranslateCommand(user, TranslateCommand.EN_RU)
            SendMessage(user.chatId, "Введите слово или фразу на английском")
        }
    }

    fun ruEnTranslateCallbackHandler(user: User): SendMessage {
        return if (!commandUtil.isChatStarted(user)) {
            commandUtil.chatNotStartedMsg(user)
        } else if (!commandUtil.isLastServiceCommandTranslate(user)) {
            commandUtil.translatorNotSelectedMsg(user)
        } else {
            commandUtil.addLastTranslateCommand(user, TranslateCommand.RU_EN)
            SendMessage(user.chatId, "Введите слово или фразу по русски")
        }
    }

    fun weatherForecastCallbackHandler(user: User): SendMessage {
        return if (!commandUtil.isChatStarted(user)) {
            commandUtil.chatNotStartedMsg(user)
        } else {
            commandUtil.addLastServiceCommand(user, ServiceCommand.WEATHER_FORECAST)
            SendMessage(user.chatId, "Введите название населенного пункта")
        }
    }

    fun accountCallbackHandler(user: User): SendMessage {
        return if (!commandUtil.isChatStarted(user)) {
            commandUtil.chatNotStartedMsg(user)
        } else {
            commandUtil.addLastServiceCommand(user, ServiceCommand.ACCOUNT)
            accountKeyboard(user)
        }
    }

    fun getAccountCallbackHandler(user: User): SendMessage {
        return if (!commandUtil.isChatStarted(user)) {
            commandUtil.chatNotStartedMsg(user)
        } else {
            commandUtil.addLastAccountCommand(user, AccountCommand.GET)
            accountService.send(AccountRequest(user.chatId, user.lastAccountCommand))!!
        }
    }

    fun saveAccountCallbackHandler(user: User): SendMessage {
        return if (!commandUtil.isChatStarted(user)) {
            commandUtil.chatNotStartedMsg(user)
        } else {
            commandUtil.addLastServiceCommand(user, ServiceCommand.ACCOUNT)
            SendMessage(user.chatId, "Получить или добавить данные о себе")
        }
    }

}