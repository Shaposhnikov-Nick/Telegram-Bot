package tgbot.telegramservice.keyboard

import tgbot.telegramservice.handler.Callback

val toMainMenuBtn = InlineKeyboardButtonWrapper("В главное меню", callbackData = Callback.MAIN_MENU.value)
val translateBtn = InlineKeyboardButtonWrapper("Переводчик", callbackData = Callback.TRANSLATE.value)
val otherBtn = InlineKeyboardButtonWrapper("Что-то другое...", callbackData = "other")
val ruToEnBtn = InlineKeyboardButtonWrapper("RU -> EN", callbackData = Callback.RU_EN.value)
val enToRuBtn = InlineKeyboardButtonWrapper("EN -> RU", callbackData = Callback.EN_RU.value)