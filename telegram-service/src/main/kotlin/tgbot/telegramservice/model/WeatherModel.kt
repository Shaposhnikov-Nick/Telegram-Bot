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
        var weatherBody = """
        ${"Населенный пункт:".bold()} $name.bold()
        ${"Описание:".bold()} $description
        ${"Температура:".bold()} ${temp.toInt()} C, ощущается как: ${feelLike.toInt()} C
        ${"Видимость:".bold()} $visibility м
        ${"Влажность:".bold()} $humidity %
        ${"Ветер:".bold()} $wind м/с, порывы: $windGust м/с
        ${"Облачность:".bold()} $clouds %
        """.trimIndent()

        rainOneHour?.let {
            weatherBody = weatherBody.plus("\n${"Дождь 1ч:".bold()} $it мм")
        }

        rainThreeHours?.let {
            weatherBody = weatherBody.plus("\n${"Дождь 3ч:".bold()} $it мм ")
        }

        snowOneHour?.let {
            weatherBody = weatherBody.plus("\n${"Снег 1ч:".bold()} $it мм")
        }

        snowThreeHours?.let {
            weatherBody = weatherBody.plus("\n${"Снег 3ч:".bold()} $it мм")
        }

        return weatherBody
    }
}