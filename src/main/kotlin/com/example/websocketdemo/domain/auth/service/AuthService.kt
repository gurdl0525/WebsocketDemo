package com.example.websocketdemo.domain.auth.service

import com.example.websocketdemo.domain.auth.controller.dto.SignUpRequest

interface AuthService {

    fun signUp(req: SignUpRequest)
}
