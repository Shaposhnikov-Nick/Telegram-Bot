package tgbot.accountservice

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.MountableFile
import tgbot.accountservice.entity.Account
import tgbot.accountservice.model.AccountDto
import tgbot.accountservice.repository.AccountRepository


@Testcontainers
abstract class BaseTest {

    @Autowired
    lateinit var accountRepository: AccountRepository
    lateinit var account: Account
    lateinit var accountDto: AccountDto
    var savedAccount: Account? = null
    val chatId = "1111"
    val incorrectChatId = "22222"

    companion object {

        @Container
        @ServiceConnection
        val container = PostgreSQLContainer("postgres:15")
            .withCopyFileToContainer(MountableFile.forClasspathResource("schema.sql"), "/docker-entrypoint-initdb.d/")
    }

    @BeforeEach
    open fun init() {
        account = Account(null, chatId, "Nick", "1@1.com", "About me")
        accountDto = AccountDto("222", "Tim", "2@2.com", "about")

        savedAccount = accountRepository.findAccountByChatId(chatId)
        if (savedAccount == null)
            savedAccount = accountRepository.save(account)
    }

}