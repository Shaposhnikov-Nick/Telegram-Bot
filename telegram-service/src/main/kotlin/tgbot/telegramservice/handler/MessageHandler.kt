package tgbot.telegramservice.handler

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import tgbot.telegramservice.entity.User
import tgbot.telegramservice.handler.util.*
import tgbot.telegramservice.keyboard.mainMenuKeyboard
import tgbot.telegramservice.model.AccountDto
import tgbot.telegramservice.model.AccountRequest
import tgbot.telegramservice.model.TranslateRequestEvent
import tgbot.telegramservice.model.WeatherRequestEvent
import tgbot.telegramservice.producer.Producer


@Component
class MessageHandler(
    val commandUtil: CommandHandlerUtil,
    val producers: Map<String, Producer>
) : Handler {
    override fun handle(update: Update, user: User): SendMessage? {
        if (!commandUtil.isChatStarted(user)) return commandUtil.chatNotStartedMsg(user)

        val complexProducer = when (commandUtil.getLastServiceCommand(user)) {
            ServiceCommand.TRANSLATE -> {
                producers["translate"]!! to TranslateRequestEvent(
                    user.chatId, update.message.text, commandUtil.getLastTranslateCommand(user)?.direction!!
                )
            }

            ServiceCommand.WEATHER_FORECAST -> {
                producers["weather"]!! to WeatherRequestEvent(
                    user.chatId, update.message.text
                )
            }

            ServiceCommand.ACCOUNT -> {
                val text = update.message.text.split(",")
                val request = AccountDto(user.chatId, text[0], text[1], text[2])
                val lastAccountCommand = commandUtil.getLastAccountCommand(user)
                producers["account"]!! to AccountRequest(user.chatId, lastAccountCommand, request)
            }

            else -> null
        }

        return if (complexProducer != null) {
            complexProducer.first.send(complexProducer.second)
        } else mainMenuKeyboard(user)

    }

}