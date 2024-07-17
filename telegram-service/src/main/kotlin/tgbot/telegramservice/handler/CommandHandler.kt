package tgbot.telegramservice.handler

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import tgbot.telegramservice.entity.User
import tgbot.telegramservice.handler.util.*


@Component
class CommandHandler(
    val commandUtil: CommandHandlerUtil
) : Handler {

    override fun handle(update: Update, user: User): SendMessage {
        return when (update.message.text) {
            MainCommand.START.value -> commandUtil.startCommandHandler(user)
            MainCommand.STOP.value -> commandUtil.stopCommandHandler(user)
            else -> SendMessage(user.chatId, "Неизвестная команда!")
        }
    }

}

enum class MainCommand(val value: String) {
    START("/start"),
    STOP("/stop"),
}

enum class ServiceCommand(val value: String) {
    TRANSLATE("/translate"),
    WEATHER_FORECAST("/weather_forecast")
}

enum class TranslateCommand(val value: String, val direction: String) {
    RU_EN("/ru-en", "ru|en"),
    EN_RU("/en-ru", "en|ru")
}