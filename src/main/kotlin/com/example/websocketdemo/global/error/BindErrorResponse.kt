package com.example.websocketdemo.global.error

import org.springframework.http.HttpStatus

data class BindErrorResponse(
    val status: HttpStatus,
    val fieldError: List<Map<String, String?>>
)