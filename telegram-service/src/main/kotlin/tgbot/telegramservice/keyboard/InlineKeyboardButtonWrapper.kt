package tgbot.telegramservice.keyboard

import org.telegram.telegrambots.meta.api.objects.LoginUrl
import org.telegram.telegrambots.meta.api.objects.games.CallbackGame
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo

/**
 * Wrapper for easier keyboard creation
 */
class InlineKeyboardButtonWrapper(
    private val text: String = "",
    private val url: String? = null,
    private val callbackData: String? = null,
    private val callbackGame: CallbackGame? = null,
    private val switchInlineQuery: String? = null,
    private val switchInlineQueryCurrentChat: String? = null,
    private val pay: Boolean? = null,
    private val loginUrl: LoginUrl? = null,
    private val webApp: WebAppInfo? = null
) : InlineKeyboardButton(text, url, callbackData, callbackGame, switchInlineQuery, switchInlineQueryCurrentChat, pay, loginUrl, webApp)