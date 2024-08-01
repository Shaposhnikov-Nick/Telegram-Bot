package tgbot.accountservice.extensions

import tgbot.accountservice.entity.Account
import tgbot.accountservice.model.AccountDto

fun Account.toDto(): AccountDto {
    return AccountDto(
        chatId,
        name,
        email,
        about
    )
}

fun AccountDto.toEntity(): Account {
    return Account(
        null,
        chatId,
        name,
        email,
        about
    )
}