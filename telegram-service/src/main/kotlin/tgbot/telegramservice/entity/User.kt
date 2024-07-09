package tgbot.telegramservice.entity

import org.springframework.data.redis.core.RedisHash
import tgbot.telegramservice.handler.MainCommand
import tgbot.telegramservice.handler.ServiceCommand
import tgbot.telegramservice.handler.TranslateCommand
import java.io.Serializable

@RedisHash("user")
data class User(
    val id: String  // equals chatId
) : Serializable {
    var chatStarted: Boolean = false
    var lastMainCommand: MainCommand = MainCommand.STOP
    lateinit var lastServiceCommand: ServiceCommand
    lateinit var lastTranslateCommand: TranslateCommand
}