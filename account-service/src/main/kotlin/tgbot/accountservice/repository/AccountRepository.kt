package tgbot.accountservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import tgbot.accountservice.entity.Account

interface AccountRepository: JpaRepository<Account, Long> {
    fun findAccountByChatId(chatId: String): Account
}