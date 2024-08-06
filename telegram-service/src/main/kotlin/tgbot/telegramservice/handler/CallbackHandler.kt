package tgbot.telegramservice.handler

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import tgbot.telegramservice.entity.User
import tgbot.telegramservice.handler.util.*


@Component
class CallbackHandler(
    val callbackUtil: CallbackHandlerUtil
) : Handler {

    override fun handle(update: Update, user: User): SendMessage {
        val chatId = update.callbackQuery.message.chatId.toString()
        val response = when (update.callbackQuery.data) {
            Callback.MAIN_MENU.value -> callbackUtil.mainMenuCallbackHandler(user)
            Callback.TRANSLATE.value -> callbackUtil.translateCallbackHandler(user)
            Callback.EN_RU.value -> callbackUtil.enRuTranslateCallbackHandler(user)
            Callback.RU_EN.value -> callbackUtil.ruEnTranslateCallbackHandler(user)
            Callback.WEATHER_FORECAST.value -> callbackUtil.weatherForecastCallbackHandler(user)
            else -> SendMessage(chatId, "Неизвестная команда!")
        }
        return response
    }
}

enum class Callback(val value: String) {
    MAIN_MENU("mainMenu"),
    TRANSLATE("translate"),
    RU_EN("ru|en"),
    EN_RU("en|ru"),
    WEATHER_FORECAST("weather"),
    ACCOUNT("Аккаунт")
}