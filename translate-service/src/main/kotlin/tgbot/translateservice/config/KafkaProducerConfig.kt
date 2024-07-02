package tgbot.translateservice.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer
import tgbot.translateservice.model.TranslateResponseEvent


@Configuration
class KafkaProducerConfig(
    val kafkaProperties: KafkaProperties
) {

    @Bean
    fun translateResponseKafkaTemplate(): KafkaTemplate<String, TranslateResponseEvent> {
        val configProps = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to kafkaProperties.producers.translateProducer.keySerializer,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to kafkaProperties.producers.translateProducer.valueSerializer,
            JsonSerializer.ADD_TYPE_INFO_HEADERS to false
        )
        val producerFactory = DefaultKafkaProducerFactory<String, TranslateResponseEvent>(configProps)
        return KafkaTemplate(producerFactory)
    }
}