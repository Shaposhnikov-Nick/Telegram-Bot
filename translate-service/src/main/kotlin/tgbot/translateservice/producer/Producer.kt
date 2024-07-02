package tgbot.translateservice.producer

interface BotEvent

interface Producer {
    suspend fun <T : BotEvent> send(event: T)
}