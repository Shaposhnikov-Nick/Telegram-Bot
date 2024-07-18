package tgbot.weatherservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "web.client")
class WebClientProperty {
    lateinit var baseUrl: String
    lateinit var token: String
}

@Configuration
@ConfigurationProperties(prefix = "kafka")
class KafkaProperties {
    lateinit var bootstrapServers: String
    lateinit var topics: Topics
    lateinit var consumers: Consumers
    lateinit var producers: Producers

    class Topics {
        lateinit var weatherRequest: String
        lateinit var weatherResponse: String
    }

    class Consumers {
        lateinit var weatherConsumer: Consumer

        class Consumer {
            lateinit var groupId: String
            lateinit var autoOffsetReset: String
            lateinit var keyDeserializer: String
            lateinit var valueDeserializer: String
            lateinit var valueDefaultType: String
        }
    }

    class Producers {
        lateinit var weatherProducer: Producer

        class Producer {
            lateinit var keySerializer: String
            lateinit var valueSerializer: String
        }
    }

}