package tgbot.telegramservice.consumer

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import tgbot.telegramservice.model.ServiceResponse
import tgbot.telegramservice.model.WeatherResponseEvent
import tgbot.telegramservice.service.TelegramBot

@Component
class WeatherForecastResponseEventHandler(
    val bot: TelegramBot
) {

    @KafkaListener(
        topics = ["weather-response-event-topic"],
        containerFactory = "weatherResponseListenerContainerFactory"
    )
    fun handler(weatherResponse: WeatherResponseEvent) {
        bot.sendResponse(ServiceResponse(weatherResponse.chatId, weatherResponse))
    }

}