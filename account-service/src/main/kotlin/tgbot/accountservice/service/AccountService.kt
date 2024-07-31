package tgbot.accountservice.service

import org.springframework.stereotype.Service
import tgbot.accountservice.extensions.toDto
import tgbot.accountservice.model.AccountDto
import tgbot.accountservice.repository.AccountRepository


interface AccountService {
    fun getAccount(chatId: String): AccountDto
    fun saveAccount(account: AccountDto)
}


@Service
class AccountServiceImpl(
    val accountRepository: AccountRepository
): AccountService {
    override fun getAccount(chatId: String): AccountDto {
        return accountRepository.findAccountByChatId(chatId).toDto()
    }

    override fun saveAccount(account: AccountDto) {
        TODO("Not yet implemented")
    }

}