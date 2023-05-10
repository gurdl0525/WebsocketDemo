package com.example.websocketdemo.domain.chat.controller.dto.response

import com.example.websocketdemo.domain.chat.entity.Chat
import com.example.websocketdemo.domain.user.controller.dto.response.UserResponse
import com.example.websocketdemo.global.util.DateUtil
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class ChatResponse(

    @JsonProperty("room_id")
    val roomId: UUID,

    @JsonProperty("is_mine")
    val isMine: Boolean,

    @JsonProperty("sent_at")
    val sentAt: String,

    @JsonProperty("user")
    val user: UserResponse,

    @JsonProperty("message")
    val message: String,


){
    companion object {
        fun of(chat: Chat, isMine: Boolean) =
            ChatResponse(
                chat.room.id!!,
                isMine,
                DateUtil.emeriedFormat(chat.createdAt),
                UserResponse.of(chat.sender), chat.message
            )
    }
}
