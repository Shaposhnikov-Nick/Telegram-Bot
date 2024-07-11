package tgbot.telegramservice.handler

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import tgbot.telegramservice.entity.User


/**
 * Main interface for handling messages for the bot
 */
interface Handler {
    fun handle(update: Update, user: User): SendMessage?
}