package tgbot.telegramservice.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import tgbot.telegramservice.config.BotProperty
import tgbot.telegramservice.entity.User
import tgbot.telegramservice.handler.*
import tgbot.telegramservice.model.TranslateResponse


@Component
class TelegramBot(
    private val botProperty: BotProperty,
    private val handlers: Map<String, Handler>,
    private val userService: UserService
) : TelegramLongPollingBot(botProperty.token) {

    val log = LoggerFactory.getLogger(this::class.java)
    override fun getBotUsername(): String = botProperty.name

    override fun onUpdateReceived(update: Update) {
        log.info("New Message ${update.message}")
        val user = getOrCreateUser(update)
        when {
            update.hasMessage() -> {
                val msg = update.message
                if (msg.isCommand)
                    execute(handlers["commandHandler"]?.handle(update, user))
                else
                    handlers["messageHandler"]?.handle(update, user)?.let { execute(it) }
            }

            update.hasCallbackQuery() -> execute(handlers["callbackHandler"]?.handle(update, user))
        }
    }

    // TODO: fix for some responses
    fun sendResponse(response: TranslateResponse) {
        execute(SendMessage(response.chatId, response.response))
    }

    private fun getOrCreateUser(update: Update): User {
        val chatId = update.message?.chatId?.toString() ?: update.callbackQuery.message.chatId.toString()
        return userService.getUserByChatId(chatId) ?: userService.saveUser(User(chatId))
    }

}