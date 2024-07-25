package tgbot.weatherservice.consumer

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import tgbot.weatherservice.model.WeatherRequestEvent
import tgbot.weatherservice.model.WeatherResponseEvent
import tgbot.weatherservice.producer.Producer
import tgbot.weatherservice.service.WeatherForecastService

@Component
class WeatherForecastRequestEventHandler(
    val weatherForecastService: WeatherForecastService,
    val weatherForecastProducer: Producer
) {

    val log = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(
        topics = ["weather-request-event-topic"],
        containerFactory = "weatherForecastRequestListenerContainerFactory"
    )
    suspend fun handler(weatherRequest: WeatherRequestEvent) {
        log.info("Request: $weatherRequest")
        val result = weatherForecastService.getWeatherForecast(weatherRequest)
        log.info("Result: $result")
        val weatherResponse = WeatherResponseEvent(
            weatherRequest.chatId,
            result.name,
            result.descrWeather[0].description,
            result.visibility,
            result.mainWeather.humidity,
            result.wind?.speed,
            result.wind?.gust,
            result.mainWeather.temp,
            result.mainWeather.feelLike,
            result.clouds?.all,
            result.rain?.oneHour,
            result.rain?.threeHours,
            result.snow?.oneHour,
            result.snow?.threeHours
        )
        weatherForecastProducer.send(weatherResponse)
    }

}