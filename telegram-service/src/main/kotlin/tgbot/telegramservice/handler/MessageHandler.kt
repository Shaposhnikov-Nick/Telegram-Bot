package tgbot.telegramservice.handler

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import tgbot.telegramservice.handler.util.chatNotStartedMsg
import tgbot.telegramservice.handler.util.getLastMainCommand
import tgbot.telegramservice.handler.util.isChatStarted


object MessageHandler {
    fun handle(msg: Message): SendMessage {
        val chatId = msg.chatId.toString()
        if (!isChatStarted(chatId)) return chatNotStartedMsg(chatId)
        if (getLastMainCommand(chatId) == BotCommandHandler.MainCommand.TRANSLATE) {
            // TODO("translate")
            return SendMessage(chatId, "Translated")
        }
        return SendMessage(chatId, "End")
    }

}