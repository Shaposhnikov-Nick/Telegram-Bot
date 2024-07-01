package tgbot.telegramservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "bot")
class BotProperty {
    var name: String = ""
    var token: String = ""
}


@Configuration
@ConfigurationProperties(prefix = "kafka")
class KafkaProperties {
    lateinit var bootstrapServers: String
    lateinit var topics: Topics
    lateinit var consumers: Consumers
    lateinit var producers: Producers

    class Topics {
        lateinit var translateRequest: String
        lateinit var translateResponse: String
    }

    class Consumers {
        lateinit var translateConsumer: Consumer

        class Consumer {
            lateinit var groupId: String
            lateinit var autoOffsetReset: String
            lateinit var keyDeserializer: String
            lateinit var valueDeserializer: String
            lateinit var valueDefaultType: String
        }
    }

    class Producers {
        lateinit var translateProducer: Producer

        class Producer {
            lateinit var keySerializer: String
            lateinit var valueSerializer: String
        }
    }

}