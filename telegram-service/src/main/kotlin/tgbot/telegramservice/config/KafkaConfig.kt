package tgbot.telegramservice.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder


@Configuration
class KafkaConfig(
    @Value("\${topic.translate.request.name}")
    val translateRequestTopicName: String
) {

    @Bean
    fun createTopic(): NewTopic {
        return TopicBuilder
            .name(translateRequestTopicName)
            .partitions(3)
            .replicas(3)
            .build()
    }
}