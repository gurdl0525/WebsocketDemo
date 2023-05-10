package com.example.websocketdemo.domain.chat.controller.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID
import javax.validation.constraints.NotNull

data class JoinSocketRoomRequest(

    @JsonProperty("is_join_room")
    @field:NotNull
    val isJoinRoom: Boolean,

    @JsonProperty("room_id")
    @field:NotNull
    val roomId: UUID,
)
