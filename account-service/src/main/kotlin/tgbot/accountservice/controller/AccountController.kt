package tgbot.accountservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tgbot.accountservice.model.AccountDto
import tgbot.accountservice.service.AccountService


@RestController
@RequestMapping("account")
class AccountController(
    val accountService: AccountService
) {

    @GetMapping("{chatId}")
    fun getAccount(@PathVariable chatId: String): AccountDto {
        return accountService.getAccount(chatId)
    }

    @PostMapping
    fun saveAccount(@RequestBody accountDto: AccountDto): AccountDto {
        return accountService.saveAccount(accountDto)
    }
}