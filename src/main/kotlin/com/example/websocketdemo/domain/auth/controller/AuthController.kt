package com.example.websocketdemo.domain.auth.controller

import com.example.websocketdemo.domain.auth.controller.dto.SignUpRequest
import com.example.websocketdemo.domain.auth.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(req: SignUpRequest) = authService.signUp(req)
}