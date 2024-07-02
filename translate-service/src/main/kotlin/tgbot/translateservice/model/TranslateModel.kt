package tgbot.translateservice.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import tgbot.translateservice.producer.BotEvent

@JsonIgnoreProperties(ignoreUnknown = true)
data class TranslateResponse(
    val matches: List<Matcher?>,
    val responseData: ResponseData,
    val responseStatus: Int
)

data class ResponseData(
    val match: Int,
    val translatedText: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Matcher(
    val segment: String,
    val source: String,
    val target: String,
    val translation: String,
)

data class TranslateRequestEvent(
    val chatId: String,
    val query: String,
    val langPair: String
)

data class TranslateResponseEvent(
    val chatId: String,
    val response: String
): BotEvent