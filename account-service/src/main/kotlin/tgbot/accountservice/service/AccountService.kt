package tgbot.accountservice.service

import org.springframework.stereotype.Service
import tgbot.accountservice.exception.AccountException
import tgbot.accountservice.extensions.toDto
import tgbot.accountservice.extensions.toEntity
import tgbot.accountservice.model.AccountDto
import tgbot.accountservice.repository.AccountRepository


interface AccountService {
    fun getAccount(chatId: String): AccountDto
    fun saveAccount(accountDto: AccountDto): AccountDto
}


@Service
class AccountServiceImpl(
    val accountRepository: AccountRepository
) : AccountService {
    override fun getAccount(chatId: String): AccountDto {
        return accountRepository.findAccountByChatId(chatId)?.toDto()
            ?: throw AccountException("Пользователь $chatId не найден")
    }

    override fun saveAccount(accountDto: AccountDto): AccountDto {
        var account = accountRepository.findAccountByChatId(accountDto.chatId)
        if (account != null) {
            account.name = accountDto.name
            account.email = accountDto.email
            account.about = accountDto.about
        } else {
            account = accountDto.toEntity()
        }
        return accountRepository.save(account).toDto()
    }

}