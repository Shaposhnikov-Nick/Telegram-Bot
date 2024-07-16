package tgbot.telegramservice.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder


@Configuration
class KafkaTopicConfig(
    private val kafkaProperties: KafkaProperties,
) {

    @Bean
    fun translateRequestTopic(): NewTopic {
        return TopicBuilder
            .name(kafkaProperties.topics.translateRequest)
            .partitions(3)
            .replicas(3)
            .build()
    }

    @Bean
    fun translateResponseTopic(): NewTopic {
        return TopicBuilder
            .name(kafkaProperties.topics.translateResponse)
            .partitions(3)
            .replicas(3)
            .build()
    }

    @Bean
    fun weatherRequestTopic(): NewTopic {
        return TopicBuilder
            .name(kafkaProperties.topics.weatherRequest)
            .partitions(3)
            .replicas(3)
            .build()
    }

    @Bean
    fun weatherResponseTopic(): NewTopic {
        return TopicBuilder
            .name(kafkaProperties.topics.weatherResponse)
            .partitions(3)
            .replicas(3)
            .build()
    }

}