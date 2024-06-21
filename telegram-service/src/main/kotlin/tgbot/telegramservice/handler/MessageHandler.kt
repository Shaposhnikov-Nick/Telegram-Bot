package tgbot.telegramservice.handler

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import tgbot.telegramservice.handler.util.chatNotStartedMsg
import tgbot.telegramservice.handler.util.getLastMainCommand
import tgbot.telegramservice.handler.util.isChatStarted
import tgbot.telegramservice.model.TranslateRequestEvent
import tgbot.telegramservice.producer.Producer
import tgbot.telegramservice.service.lastTranslateCommand


@Component
class MessageHandler(
    val producers: Map<String, Producer>
) {
    fun handle(msg: Message): SendMessage {
        val chatId = msg.chatId.toString()
        if (!isChatStarted(chatId)) return chatNotStartedMsg(chatId)

        val producer = when (getLastMainCommand(chatId)) {
            BotCommandHandler.MainCommand.TRANSLATE -> producers["translate"]!!
            BotCommandHandler.MainCommand.START -> TODO()
            BotCommandHandler.MainCommand.STOP -> TODO()
            null -> TODO()
        }
        producer.send(TranslateRequestEvent(chatId, msg.text, lastTranslateCommand[chatId]?.value!!.substring(1)))
        return SendMessage(chatId, "Translated")
    }

}