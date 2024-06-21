package tgbot.telegramservice.producer

interface BotEvent

interface Producer {
    fun <T : BotEvent> send(event: T)
}

