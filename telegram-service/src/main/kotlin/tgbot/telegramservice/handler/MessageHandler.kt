package tgbot.telegramservice.handler

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import tgbot.telegramservice.handler.util.*
import tgbot.telegramservice.keyboard.mainMenuKeyboard
import tgbot.telegramservice.model.TranslateRequestEvent
import tgbot.telegramservice.producer.Producer


@Component
class MessageHandler(
    val producers: Map<String, Producer>
) {
    fun handle(msg: Message): SendMessage? {
        val chatId = msg.chatId.toString()
        if (!isChatStarted(chatId)) return chatNotStartedMsg(chatId)

        val producer = when (getLastServiceCommand(chatId)) {
            ServiceCommand.TRANSLATE -> producers["translate"]!!
            else -> null
        }

        return if (producer != null) {
            producer.send(TranslateRequestEvent(chatId, msg.text, getLastTranslateCommand(chatId)?.direction!!))
        } else  mainMenuKeyboard(chatId)

    }

}