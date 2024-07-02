package tgbot.translateservice.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer
import tgbot.translateservice.model.TranslateRequestEvent


@Configuration
class KafkaConsumerConfig(
    val kafkaProperties: KafkaProperties
) {

    @Bean
    fun translateRequestListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, TranslateRequestEvent> {
        val configProps = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to kafkaProperties.consumers.translateConsumer.groupId,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to kafkaProperties.consumers.translateConsumer.autoOffsetReset,
            ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS to kafkaProperties.consumers.translateConsumer.valueDeserializer,
            ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS to kafkaProperties.consumers.translateConsumer.keyDeserializer,
            JsonDeserializer.TRUSTED_PACKAGES to "*",
            JsonDeserializer.VALUE_DEFAULT_TYPE to kafkaProperties.consumers.translateConsumer.valueDefaultType
        )

        val consumerFactory = DefaultKafkaConsumerFactory(
            configProps, StringDeserializer(), JsonDeserializer(TranslateRequestEvent::class.java)
        )

        val listenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, TranslateRequestEvent>()
        listenerContainerFactory.consumerFactory = consumerFactory
        return listenerContainerFactory
    }
}