package tgbot.accountservice.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import tgbot.accountservice.BaseTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest : BaseTest() {

    @Test
    fun `Save account`() {
        assertThat(savedAccount)
            .isNotNull()
            .hasFieldOrProperty("id").isNotNull()
            .hasFieldOrPropertyWithValue("chatId", account.chatId)
            .hasFieldOrPropertyWithValue("name", account.name)
            .hasFieldOrPropertyWithValue("email", account.email)
            .hasFieldOrPropertyWithValue("about", account.about)
    }

    @Test
    fun `Get account by chatId`() {
        val saved = accountRepository.findAccountByChatId(chatId)

        assertThat(saved)
            .isNotNull()
            .hasFieldOrProperty("id").isNotNull()
            .hasFieldOrPropertyWithValue("chatId", account.chatId)
            .hasFieldOrPropertyWithValue("name", account.name)
            .hasFieldOrPropertyWithValue("email", account.email)
            .hasFieldOrPropertyWithValue("about", account.about)
    }
}