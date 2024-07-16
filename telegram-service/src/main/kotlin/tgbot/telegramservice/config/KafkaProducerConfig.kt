package tgbot.telegramservice.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer
import tgbot.telegramservice.model.TranslateRequestEvent
import tgbot.telegramservice.model.WeatherRequestEvent


@Configuration
class KafkaProducerConfig(
    val kafkaProperties: KafkaProperties
) {

    @Bean
    fun translateRequestKafkaTemplate(): KafkaTemplate<String, TranslateRequestEvent> {
        val translateProducerProps = kafkaProperties.producers["translate-producer"]
        val configProps = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to translateProducerProps?.keySerializer,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to translateProducerProps?.valueSerializer,
            JsonSerializer.ADD_TYPE_INFO_HEADERS to false
        )
        val producerFactory = DefaultKafkaProducerFactory<String, TranslateRequestEvent>(configProps)
        return KafkaTemplate(producerFactory)
    }

    @Bean
    fun weatherRequestKafkaTemplate(): KafkaTemplate<String, WeatherRequestEvent> {
        val weatherProducerProps = kafkaProperties.producers["weather-producer"]
        val configProps = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to weatherProducerProps?.keySerializer,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to weatherProducerProps?.valueSerializer,
            JsonSerializer.ADD_TYPE_INFO_HEADERS to false
        )
        val producerFactory = DefaultKafkaProducerFactory<String, WeatherRequestEvent>(configProps)
        return KafkaTemplate(producerFactory)
    }

}