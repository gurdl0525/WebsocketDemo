package com.example.websocketdemo.global.error

import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.validation.FieldError

data class ErrorResponse(
    val status: Int,
    val code: String,
    val message: String
) {

    companion object {

        fun of(errorCode: ErrorCode) = ErrorResponse(
            errorCode.status(),
            errorCode.code(),
            errorCode.message()
        )

        fun of(e: BindException): BindErrorResponse {

            val errorMap = HashMap<String, String?>()

            for (error: FieldError in e.fieldErrors) {
                errorMap[error.field] = error.defaultMessage
            }

            return BindErrorResponse(HttpStatus.BAD_REQUEST, listOf(errorMap))
        }
    }
}