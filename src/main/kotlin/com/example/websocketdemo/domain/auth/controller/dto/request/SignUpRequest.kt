package com.example.websocketdemo.domain.auth.controller.dto.request

import javax.validation.constraints.NotBlank

data class SignUpRequest(

    @field:NotBlank(message = "공백 불가")
    val accountId: String,

    @field:NotBlank(message = "공백 불가")
    val password: String
)
