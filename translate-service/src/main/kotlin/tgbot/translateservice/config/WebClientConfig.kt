package tgbot.translateservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun wecClient(builder: WebClient.Builder, webProperty: WebClientProperty): WebClient {
        return builder
            .baseUrl(webProperty.baseUrl)
            .codecs { configurer -> configurer.defaultCodecs().maxInMemorySize(-1) }
            .build()
    }

}


@Configuration
@ConfigurationProperties(prefix = "web.client")
class WebClientProperty {
    var baseUrl: String = ""
}