package tgbot.telegramservice.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import tgbot.telegramservice.config.BotProperty
import tgbot.telegramservice.handler.*
import tgbot.telegramservice.model.TranslateResponse
import java.util.concurrent.ConcurrentHashMap


//TODO: change implementation to Redis
val lastMainCommand: ConcurrentHashMap<String, MainCommand> = ConcurrentHashMap(16, 75.0F, 15)

val lastServiceCommand: ConcurrentHashMap<String, ServiceCommand> = ConcurrentHashMap(16, 75.0F, 15)

val lastTranslateCommand: ConcurrentHashMap<String, TranslateCommand> =
    ConcurrentHashMap(16, 75.0F, 15)

val chatStarted: ConcurrentHashMap<String, Boolean> = ConcurrentHashMap(16, 75.0F, 15)

@Component
class TelegramBot(
    private val botProperty: BotProperty,
    private val messageHandler: MessageHandler,
    private val callbackHandler: CallbackHandler,
    private val commandHandler: CommandHandler
) : TelegramLongPollingBot(botProperty.token) {

    val log = LoggerFactory.getLogger(this::class.java)
    override fun getBotUsername(): String = botProperty.name

    override fun onUpdateReceived(update: Update) {
        log.info("New Message ${update.message}")
        when {
            update.hasMessage() -> {
                val msg = update.message
                if (msg.isCommand)
                    execute(commandHandler.handle(msg))
                else
                    messageHandler.handle(msg)?.let { execute(it) }
            }

            update.hasCallbackQuery() -> execute(callbackHandler.handle(update))
        }
    }

    // TODO: fix for some responses
    fun sendResponse(response: TranslateResponse) {
        execute(SendMessage(response.chatId, response.response))
    }

}

