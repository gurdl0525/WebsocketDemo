package com.example.websocketdemo.domain.chat.controller.dto.request

import java.util.*
import javax.validation.constraints.NotNull

data class JoinSocketRoomRequest(

    @field:NotNull(message = "공백 불가")
    val isJoinRoom: Boolean,

    @field:NotNull(message = "공백 불가")
    val roomId: UUID,
)
