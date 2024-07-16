package tgbot.telegramservice.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer
import tgbot.telegramservice.model.TranslateResponseEvent
import tgbot.telegramservice.model.WeatherResponseEvent


@Configuration
class KafkaConsumerConfig(
    val kafkaProperties: KafkaProperties
) {

    @Bean
    fun translateResponseListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, TranslateResponseEvent> {
        val translateConsumerProps = kafkaProperties.consumers["translate-consumer"]
        val configProps = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to translateConsumerProps?.groupId,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to translateConsumerProps?.autoOffsetReset,
            ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS to translateConsumerProps?.valueDeserializer,
            ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS to translateConsumerProps?.keyDeserializer,
            JsonDeserializer.TRUSTED_PACKAGES to "*",
            JsonDeserializer.VALUE_DEFAULT_TYPE to translateConsumerProps?.valueDefaultType
        )

        val consumerFactory = DefaultKafkaConsumerFactory(
            configProps, StringDeserializer(), JsonDeserializer(TranslateResponseEvent::class.java)
        )

        val listenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, TranslateResponseEvent>()
        listenerContainerFactory.consumerFactory = consumerFactory
        return listenerContainerFactory
    }

    @Bean
    fun weatherResponseListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, WeatherResponseEvent> {
        val weatherConsumerProps = kafkaProperties.consumers["weather-consumer"]
        val configProps = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to weatherConsumerProps?.groupId,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to weatherConsumerProps?.autoOffsetReset,
            ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS to weatherConsumerProps?.valueDeserializer,
            ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS to weatherConsumerProps?.keyDeserializer,
            JsonDeserializer.TRUSTED_PACKAGES to "*",
            JsonDeserializer.VALUE_DEFAULT_TYPE to weatherConsumerProps?.valueDefaultType
        )

        val consumerFactory = DefaultKafkaConsumerFactory(
            configProps, StringDeserializer(), JsonDeserializer(WeatherResponseEvent::class.java)
        )

        val listenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, WeatherResponseEvent>()
        listenerContainerFactory.consumerFactory = consumerFactory
        return listenerContainerFactory
    }
}