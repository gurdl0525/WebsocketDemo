package com.example.websocketdemo.global.error

import com.example.websocketdemo.global.exception.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    protected fun handleBindException(e: BindException): BindErrorResponse = ErrorResponse.of(e)


    @ExceptionHandler(BusinessException::class)
    protected fun customExceptionHandle(e: BusinessException): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse.of(e.errorCode)
        val status = HttpStatus.valueOf(e.errorCode.status())
        return ResponseEntity(body, status)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun handleHttpMessageNotReadable(e: HttpMessageNotReadableException) =
        ResponseEntity(
            ErrorResponse.of(e),
            HttpStatus.BAD_REQUEST
        )
}