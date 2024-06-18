package tgbot.telegramservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import tgbot.telegramservice.service.TelegramBot

@Configuration
class BotConfig {

    @Bean
    fun telegramBotsApi(bot: TelegramBot): TelegramBotsApi =
        TelegramBotsApi(DefaultBotSession::class.java).apply {
            registerBot(bot)
        }

}