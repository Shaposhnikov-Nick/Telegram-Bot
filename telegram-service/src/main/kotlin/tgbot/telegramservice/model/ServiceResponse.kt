package tgbot.telegramservice.model

class ServiceResponse<T: Event> (
    val chatId: String,
    val body: T
)

interface Event {
    fun getMessageBody(): String
}
