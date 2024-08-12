package tgbot.telegramservice.producer

import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientException
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import tgbot.telegramservice.handler.AccountCommand
import tgbot.telegramservice.model.AccountDto
import tgbot.telegramservice.model.AccountRequest


@Service("account")
class AccountService(
    val restClient: RestClient
) : Producer {
    override fun <T : BotEvent> send(event: T): SendMessage? {
        if (event !is AccountRequest) return null

        val response = when (event.lastAccountCommand) {
            AccountCommand.GET -> {
                try {
                    restClient
                        .get()
                        .uri("/account/${event.chatId}")
                        .retrieve()
                        .body(AccountDto::class.java)
                } catch (e: RestClientException) {
                    AccountDto(event.chatId, "Нет данных")
                }
            }

            AccountCommand.SAVE -> {
                val request = AccountDto(event.chatId, event.request!!.name, event.request.email, event.request.about)
                restClient
                    .post()
                    .uri("/account")
                    .body(request)
                    .retrieve()
                    .body(AccountDto::class.java)
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