package tgbot.weatherservice.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import tgbot.weatherservice.producer.BotEvent


data class WeatherRequestEvent(
    val chatId: String,
    val query: String,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherResponse(
    val name: String,
    @JsonProperty("weather")
    val descrWeather: List<WeatherDescription>,
    @JsonProperty("main")
    val mainWeather: MainWeather,
    val visibility: Int,
    val wind: Wind?,
    val clouds: Clouds?,
    val rain: Rain?,
    val snow: Snow?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherDescription(
    val description: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class MainWeather(
    val humidity: Double,
    val temp: Double,
    @JsonProperty("feels_like")
    val feelLike: Double
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Wind(
    val speed: Double?,
    val gust: Double?
)

data class Clouds(
    val all: Int
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Rain(
    val oneHour: Double?,
    val threeHours: Double?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Snow(
    val oneHour: Double?,
    val threeHours: Double?
)

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
) : BotEvent