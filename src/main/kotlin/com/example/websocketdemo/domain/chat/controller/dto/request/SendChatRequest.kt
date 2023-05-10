package com.example.websocketdemo.domain.chat.controller.dto.request

import javax.validation.constraints.NotBlank

data class SendChatRequest(

    @field:NotBlank(message = "공백 불가")
    val message: String
)
