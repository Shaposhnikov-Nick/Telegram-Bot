package tgbot.telegramservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient


@Configuration
class RestClientConfig(
    val restClientProperty: RestClientProperty
) {

    @Bean
    fun restClient(): RestClient {
        return RestClient
            .builder()
            .baseUrl(restClientProperty.baseUrl)
            .build()
    }

}