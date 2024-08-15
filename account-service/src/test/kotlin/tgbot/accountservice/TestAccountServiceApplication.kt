package tgbot.accountservice

import org.springframework.boot.fromApplication


fun main(args: Array<String>) {
	fromApplication<AccountServiceApplication>().run(*args)
}
