package com.example.websocketdemo.domain.chat.controller.dto.request

import javax.validation.constraints.NotNull

data class CreateRoomRequest(

    @NotNull(message = "공백 불가")
    val accountId: String?
)
