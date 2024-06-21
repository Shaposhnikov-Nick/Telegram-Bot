package tgbot.telegramservice.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import tgbot.telegramservice.config.BotProperty
import tgbot.telegramservice.handler.BotCommandHandler
import tgbot.telegramservice.handler.CallbackHandler
import tgbot.telegramservice.handler.MessageHandler
import java.util.concurrent.ConcurrentHashMap


//TODO: change implementation to Redis
val lastMainCommand: ConcurrentHashMap<String, BotCommandHandler.MainCommand> = ConcurrentHashMap(16, 75.0F, 15)

val lastTranslateCommand: ConcurrentHashMap<String, BotCommandHandler.TranslateCommand> =
    ConcurrentHashMap(16, 75.0F, 15)

val chatStarted: ConcurrentHashMap<String, Boolean> = ConcurrentHashMap(16, 75.0F, 15)

@Component
class TelegramBot(
    private val botProperty: BotProperty,
    private val messageHandler: MessageHandler
) : TelegramLongPollingBot(botProperty.token) {

    val log = LoggerFactory.getLogger(this::class.java)
    override fun getBotUsername(): String = botProperty.name

    override fun onUpdateReceived(update: Update) {
        log.info("New Message ${update.message}")
        if (update.hasMessage()) {
            val msg = update.message
            if (msg.isCommand)
                execute(BotCommandHandler.handle(msg))
            else
                execute(messageHandler.handle(msg))
        }
        if (update.hasCallbackQuery()) {
            CallbackHandler.handle(update)
        }
    }

}

