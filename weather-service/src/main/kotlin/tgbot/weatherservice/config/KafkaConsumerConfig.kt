package tgbot.weatherservice.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer
import tgbot.weatherservice.model.WeatherRequestEvent


@Configuration
class KafkaConsumerConfig(
    val kafkaProperties: KafkaProperties
) {

    @Bean
    fun weatherForecastRequestListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, WeatherRequestEvent> {
        val configProps = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to kafkaProperties.consumers.weatherConsumer.groupId,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to kafkaProperties.consumers.weatherConsumer.autoOffsetReset,
            ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS to kafkaProperties.consumers.weatherConsumer.valueDeserializer,
            ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS to kafkaProperties.consumers.weatherConsumer.keyDeserializer,
            JsonDeserializer.TRUSTED_PACKAGES to "*",
            JsonDeserializer.VALUE_DEFAULT_TYPE to kafkaProperties.consumers.weatherConsumer.valueDefaultType
        )

        val consumerFactory = DefaultKafkaConsumerFactory(
            configProps, StringDeserializer(), JsonDeserializer(WeatherRequestEvent::class.java)
        )

        val listenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, WeatherRequestEvent>()
        listenerContainerFactory.consumerFactory = consumerFactory
        return listenerContainerFactory
    }
}