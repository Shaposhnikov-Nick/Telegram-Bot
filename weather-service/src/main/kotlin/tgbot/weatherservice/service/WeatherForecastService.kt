package tgbot.weatherservice.service

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.util.retry.Retry
import tgbot.weatherservice.config.WebClientProperty
import tgbot.weatherservice.model.WeatherRequestEvent
import tgbot.weatherservice.model.WeatherResponse
import java.time.Duration


interface WeatherForecastService {
    suspend fun getWeatherForecast(weatherRequest: WeatherRequestEvent): WeatherResponse
}


@Service
class WeatherForecastServiceImpl(
    val webClient: WebClient,
    val webProperty: WebClientProperty
) : WeatherForecastService {

    override suspend fun getWeatherForecast(weatherRequest: WeatherRequestEvent): WeatherResponse {
        return webClient
            .get()
            .uri { uriBuilder ->
                uriBuilder
                    .queryParam("appid", webProperty.token)
                    .queryParam("q", weatherRequest.query)
                    .build()
            }
            .retrieve()
            .bodyToMono(WeatherResponse::class.java)
            .retryWhen(retryConf())
            .awaitSingle()
    }

    private fun retryConf(): Retry {
        return Retry.fixedDelay(3, Duration.ofSeconds(1))
            .filter { throwable ->
                throwable is WebClientResponseException.InternalServerError // Retry on server errors
            }
            .onRetryExhaustedThrow { _, retrySignal ->
                throw RuntimeException("Resource service failed to respond after ${retrySignal.totalRetries()} retries")
            }
    }

}