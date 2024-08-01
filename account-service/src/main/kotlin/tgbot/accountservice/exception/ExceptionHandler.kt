package tgbot.accountservice.exception

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime


@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun handlerAllException(e: Exception): ResponseEntity<ExceptionResponse> {
        val response = ExceptionResponse(
            LocalDateTime.now(),
            e.message ?: "Unknown error",
            HttpStatus.INTERNAL_SERVER_ERROR.name,
            HttpStatus.INTERNAL_SERVER_ERROR.value()
        )
        return ResponseEntity.internalServerError().body(response)
    }

    @ExceptionHandler(value = [AccountException::class])
    fun accountExceptionHandler(e: Exception): ResponseEntity<ExceptionResponse> {
        val response = ExceptionResponse(
            LocalDateTime.now(),
            e.message ?: "Unknown error",
            HttpStatus.BAD_REQUEST.name,
            HttpStatus.BAD_REQUEST.value()
        )
        return ResponseEntity.badRequest().body(response)
    }
}

data class ExceptionResponse(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    val timestamp: LocalDateTime,
    val message: String,
    val status: String,
    val code: Int,
)

data class AccountException(override val message: String) : RuntimeException(message)