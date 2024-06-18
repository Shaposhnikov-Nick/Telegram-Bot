package tgbot.telegramservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import tgbot.telegramservice.service.IncomingMessageProcessing

@Configuration
class BotConfig {

    @Bean
    fun telegramBotsApi(bot: IncomingMessageProcessing): TelegramBotsApi =
        TelegramBotsApi(DefaultBotSession::class.java).apply {
            registerBot(bot)
        }

}