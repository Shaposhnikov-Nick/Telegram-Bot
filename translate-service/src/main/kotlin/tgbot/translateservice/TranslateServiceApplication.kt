package tgbot.translateservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TranslateServiceApplication

fun main(args: Array<String>) {
	runApplication<TranslateServiceApplication>(*args)
}
