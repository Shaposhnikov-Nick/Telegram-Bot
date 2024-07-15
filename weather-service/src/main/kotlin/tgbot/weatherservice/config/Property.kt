package tgbot.weatherservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "web.client")
class WebClientProperty {
    var baseUrl: String = ""
}