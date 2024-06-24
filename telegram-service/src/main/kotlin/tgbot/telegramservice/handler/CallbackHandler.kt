package tgbot.telegramservice.handler

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import tgbot.telegramservice.handler.util.*
import tgbot.telegramservice.keyboard.mainMenuKeyboard


@Component
class CallbackHandler {

    fun handle(update: Update): SendMessage {
        val chatId = update.callbackQuery.message.chatId.toString()
        val response = when (update.callbackQuery.data) {
            Callback.MAIN_MENU.value -> mainMenuCallbackHandler(chatId)
            Callback.TRANSLATE.value -> translateCallbackHandler(chatId)
            Callback.EN_RU.value -> enRuTranslateCallbackHandler(chatId)
            Callback.RU_EN.value -> ruEnTranslateCallbackHandler(chatId)
            else -> SendMessage(chatId, "Неизвестная команда!")
        }
        return response
    }
}

enum class Callback(val value: String) {
    MAIN_MENU("mainMenu"),
    TRANSLATE("translate"),
    RU_EN("ru|en"),
    EN_RU("en|ru")
}