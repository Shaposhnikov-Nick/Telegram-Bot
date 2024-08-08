package tgbot.telegramservice.model

import tgbot.telegramservice.extensions.bold
import tgbot.telegramservice.handler.AccountCommand
import tgbot.telegramservice.producer.BotEvent


data class AccountDto(
    val chatId: String,
    val name: String,
    val email: String? = null,
    val about: String? = null
) {
    fun getMessageBody(): String {
        return """
        ${"Chat id:".bold()} $chatId
        ${"Имя:".bold()} $name
        ${"E-mail:".bold()} ${email ?: "Нет данных"}
        ${"Обо мне:".bold()} ${about ?: "Нет данных"}
        """.trimIndent()
    }
}

data class AccountRequest(
    val chatId: String,
    val lastAccountCommand: AccountCommand,
    val request: AccountDto? = null
) : BotEvent