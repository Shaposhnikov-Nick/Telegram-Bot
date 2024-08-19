package tgbot.accountservice.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import tgbot.accountservice.BaseTest
import tgbot.accountservice.exception.AccountException
import tgbot.accountservice.model.AccountDto


@SpringBootTest
class AccountServiceImplTest : BaseTest() {

    @Autowired
    lateinit var accountService: AccountService

    @Test
    fun `Get account by correct chatId`() {
        val account = accountService.getAccount(chatId)

        assertThat(account).isNotNull()
    }

    @Test
    fun `Get account by incorrect chatId`() {

        assertThrows<AccountException> { accountService.getAccount(incorrectChatId) }
    }

    @Test
    fun `Save new account`() {
        val saved = accountService.saveAccount(accountDto)

        assertThat(saved)
            .hasFieldOrPropertyWithValue("chatId", accountDto.chatId)
            .hasFieldOrPropertyWithValue("name", accountDto.name)
            .hasFieldOrPropertyWithValue("email", accountDto.email)
            .hasFieldOrPropertyWithValue("about", accountDto.about)
    }

    @Test
    fun `Update account`() {
        val existedAccountDto = AccountDto(chatId, "Nick", "11@1.com", "About me 2")
        val saved = accountService.saveAccount(existedAccountDto)

        assertThat(saved)
            .hasFieldOrPropertyWithValue("chatId", existedAccountDto.chatId)
            .hasFieldOrPropertyWithValue("name", existedAccountDto.name)
            .hasFieldOrPropertyWithValue("email", existedAccountDto.email)
            .hasFieldOrPropertyWithValue("about", existedAccountDto.about)
    }
}