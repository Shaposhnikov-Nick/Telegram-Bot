package tgbot.weatherservice.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer
import tgbot.weatherservice.model.WeatherResponseEvent


@Configuration
class KafkaProducerConfig(
    val kafkaProperties: KafkaProperties
) {

    @Bean
    fun weatherForecastResponseKafkaTemplate(): KafkaTemplate<String, WeatherResponseEvent> {
        val configProps = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to kafkaProperties.producers.weatherProducer.keySerializer,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to kafkaProperties.producers.weatherProducer.valueSerializer,
            JsonSerializer.ADD_TYPE_INFO_HEADERS to false
        )
        val producerFactory = DefaultKafkaProducerFactory<String, WeatherResponseEvent>(configProps)
        return KafkaTemplate(producerFactory)
    }
}