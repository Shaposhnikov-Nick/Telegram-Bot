package tgbot.telegramservice.model

import tgbot.telegramservice.extensions.bold
import tgbot.telegramservice.producer.BotEvent


data class WeatherRequestEvent(
    val chatId: String,
    val query: String,
) : BotEvent


data class WeatherResponseEvent(
    val chatId: String,
    val name: String,
    val description: String,
    val visibility: Int,
    val humidity: Double,
    val wind: Double?,
    val windGust: Double?,
    val temp: Double,
    val feelLike: Double,
    val clouds: Int?,
    val rainOneHour: Double?,
    val rainThreeHours: Double?,
    val snowOneHour: Double?,
    val snowThreeHours: Double?
) : Event {
    override fun getMessageBody(): String {
        val weatherAsString = """
        ${"Населенный пункт:".bold()} ${name.bold()}
        Описание: $description
        Температура: ${temp.toInt()} C, ощущается как: ${feelLike.toInt()} C
        Видимость: $visibility м
        Влажность: $humidity %
        Ветер: $wind м/с, порывы: $windGust м/с
        Облачность: $clouds %
        """.trimIndent()

        rainOneHour?.let {
            weatherAsString.plus("""
                Дождь 1ч: $it мм
            """.trimIndent())
        }

        rainThreeHours?.let {
            weatherAsString.plus("""
                Дождь 3ч: $it мм
            """.trimIndent())
        }

        snowOneHour?.let {
            weatherAsString.plus("""
                Снег 1ч: $it мм
            """.trimIndent())
        }

        snowThreeHours?.let {
            weatherAsString.plus("""
                Снег 1ч: $it мм
            """.trimIndent())
        }

        return weatherAsString
    }
}