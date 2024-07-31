package tgbot.accountservice.model

data class AccountDto(
    val chatId: String,
    val name: String,
    val email: String? = null,
    val about: String? = null
)