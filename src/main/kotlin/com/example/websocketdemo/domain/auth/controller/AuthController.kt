package com.example.websocketdemo.domain.auth.controller

import com.example.websocketdemo.domain.auth.controller.dto.request.LoginRequest
import com.example.websocketdemo.domain.auth.controller.dto.request.SignUpRequest
import com.example.websocketdemo.domain.auth.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
@Validated
class AuthController(
    private val authService: AuthService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(
        @Valid @RequestBody
        req: SignUpRequest
    ) = authService.signUp(req)

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody
        req: LoginRequest
    ) = authService.login(req)

    @PostMapping("/re-issue")
    fun reissue() = authService.reissue()
}