package tgbot.telegramservice.keyboard

import tgbot.telegramservice.handler.Callback

val toMainMenuBtn = InlineKeyboardButtonWrapper("В главное меню", callbackData = Callback.MAIN_MENU.value)
val translateBtn = InlineKeyboardButtonWrapper("Переводчик", callbackData = Callback.TRANSLATE.value)
val weatherBtn = InlineKeyboardButtonWrapper("Прогноз погоды", callbackData = Callback.WEATHER_FORECAST.value)
val accountBtn = InlineKeyboardButtonWrapper("Аккаунт", callbackData = Callback.ACCOUNT.value)
val otherBtn = InlineKeyboardButtonWrapper("Что-то другое...", callbackData = "other")
val ruToEnBtn = InlineKeyboardButtonWrapper("RU -> EN", callbackData = Callback.RU_EN.value)
val enToRuBtn = InlineKeyboardButtonWrapper("EN -> RU", callbackData = Callback.EN_RU.value)
val getAccountBtn = InlineKeyboardButtonWrapper("Получить", callbackData = Callback.GET_ACCOUNT.value)
val saveAccountBtn = InlineKeyboardButtonWrapper("Сохранить", callbackData = Callback.SAVE_ACCOUNT.value)