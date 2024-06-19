package tgbot.translateservice.service

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.util.retry.Retry
import tgbot.translateservice.model.TranslateRequestEvent
import tgbot.translateservice.model.TranslateResponse
import java.time.Duration


interface TranslateService {
    suspend fun translate(translateRequest: TranslateRequestEvent): TranslateResponse
}


@Service
class TranslateServiceImpl(
    val webClient: WebClient
) : TranslateService {

    override suspend fun translate(translateRequest: TranslateRequestEvent): TranslateResponse {
        return webClient
            .get()
            .uri { uriBuilder ->
                uriBuilder
                    .queryParam("q", translateRequest.query)
                    .queryParam("langpair", translateRequest.langPair)
                    .build()
            }
            .retrieve()
            .bodyToMono(TranslateResponse::class.java)
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

