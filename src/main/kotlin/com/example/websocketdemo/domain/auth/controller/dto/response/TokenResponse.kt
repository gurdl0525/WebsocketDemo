package com.example.websocketdemo.domain.auth.controller.dto.response

data class TokenResponse (
    val accessToken: String,
    val refreshToken: String
)