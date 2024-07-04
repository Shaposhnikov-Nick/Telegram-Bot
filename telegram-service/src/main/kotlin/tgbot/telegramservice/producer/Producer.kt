package tgbot.telegramservice.producer

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

interface BotEvent

interface Producer {
    fun <T : BotEvent> send(event: T): SendMessage?
}

