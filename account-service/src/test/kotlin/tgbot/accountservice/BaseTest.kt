package tgbot.accountservice

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.MountableFile
import tgbot.accountservice.entity.Account
import tgbot.accountservice.repository.AccountRepository


@Testcontainers
abstract class BaseTest {

    @Autowired
    lateinit var accountRepository: AccountRepository

    val chatId = "1111"
    lateinit var account: Account

    companion object {

        @Container
        @ServiceConnection
        val container = PostgreSQLContainer("postgres:15")
            .withCopyFileToContainer(MountableFile.forClasspathResource("schema.sql"), "/docker-entrypoint-initdb.d/")
    }

    @BeforeEach
    open fun init() {
        account = Account(null, chatId, "Nick", "1@1.com", "About me")
    }

}