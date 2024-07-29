package tgbot.accountservice

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<AccountServiceApplication>().with(TestcontainersConfiguration::class).run(*args)
}
