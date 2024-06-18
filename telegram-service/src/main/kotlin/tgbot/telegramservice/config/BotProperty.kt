package tgbot.telegramservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "bot")
class BotProperty {
    var name: String = ""
    var token: String = ""
}