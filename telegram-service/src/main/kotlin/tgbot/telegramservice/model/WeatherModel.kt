package tgbot.telegramservice.model

import tgbot.telegramservice.producer.BotEvent


data class WeatherRequestEvent(
    val chatId: String,
    val query: String,
): BotEvent


data class WeatherResponseEvent(
    val chatId: String,
    val name: String,
    val description: String,
    val humidity: Double,
    val wind: Double,
    val temp: Double,
    val feelLike: Double
)