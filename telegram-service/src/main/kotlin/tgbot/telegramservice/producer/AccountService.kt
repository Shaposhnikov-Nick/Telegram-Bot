package tgbot.telegramservice.producer

import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientException
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import tgbot.telegramservice.config.RestClientProperty
import tgbot.telegramservice.handler.AccountCommand
import tgbot.telegramservice.model.AccountDto
import tgbot.telegramservice.model.AccountRequest
import java.util.Base64


@Service("account")
class AccountService(
    val restClient: RestClient,
    val property: RestClientProperty
) : Producer {
    override fun <T : BotEvent> send(event: T): SendMessage? {
        if (event !is AccountRequest) return null
        val basicToken = "Basic ${Base64.getEncoder().encodeToString((property.login + ":" + property.password).toByteArray())}"
        val response = when (event.lastAccountCommand) {
            AccountCommand.GET -> {
                try {
                    restClient
                        .get()
                        .uri("${property.get}/${event.chatId}")
                        .header(HttpHeaders.AUTHORIZATION, basicToken)
                        .retrieve()
                        .body(AccountDto::class.java)
                } catch (e: RestClientException) {
                    AccountDto(event.chatId, "Нет данных")
                }
            }

            AccountCommand.SAVE -> {
                val request = AccountDto(event.chatId, event.request!!.name, event.request.email, event.request.about)
                try {
                    restClient
                        .post()
                        .uri(property.save)
                        .header(HttpHeaders.AUTHORIZATION, basicToken)
                        .body(request)
                        .retrieve()
                        .body(AccountDto::class.java)
                } catch (e: RestClientException) {
                    AccountDto(event.chatId, "Нет данных")
                }
            }
        }

        return SendMessage.builder()
            .chatId(event.chatId)
            .text(response!!.getMessageBody())
            .disableWebPagePreview(false)
            .parseMode("html")
            .build()
    }
}