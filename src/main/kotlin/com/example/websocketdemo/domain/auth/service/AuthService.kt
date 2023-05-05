package com.example.websocketdemo.domain.auth.service

import com.example.websocketdemo.domain.auth.controller.dto.request.LoginRequest
import com.example.websocketdemo.domain.auth.controller.dto.request.SignUpRequest
import com.example.websocketdemo.domain.auth.controller.dto.response.TokenResponse

interface AuthService {

    fun signUp(req: SignUpRequest)

    fun login(req: LoginRequest): TokenResponse

    fun reissue(): TokenResponse
}
