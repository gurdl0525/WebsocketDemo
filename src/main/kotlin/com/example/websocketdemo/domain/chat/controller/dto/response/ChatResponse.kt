package com.example.websocketdemo.domain.chat.controller.dto.response

import com.example.websocketdemo.domain.chat.entity.Chat
import com.example.websocketdemo.domain.user.controller.dto.response.UserResponse
import com.example.websocketdemo.global.util.DateUtil
import java.util.*

data class ChatResponse(

    val roomId: UUID,

    val isMine: Boolean,

    val sentAt: String,

    val user: UserResponse,

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
