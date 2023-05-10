package com.example.websocketdemo.domain.chat.controller.dto.response

import com.example.websocketdemo.domain.chat.entity.Room
import com.example.websocketdemo.domain.chat.entity.RoomUser
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.global.util.DateUtil
import java.util.*

data class RoomResponse(
    val roomId: UUID,
    val roomName: String,
    val isRead: Boolean,
    val lastChat: LastChat
){
    companion object{
        fun of(roomUser: RoomUser): RoomResponse {
            val user: User = roomUser.user
            val room: Room = roomUser.room
            return RoomResponse(
                room.id!!,
                room.getOtherUser(user).accountId,
                room.lastChat.lastSentAt.isBefore(roomUser.lastRead),
                LastChat(
                    room.lastChat.lastMessage,
                    DateUtil.toTimeAgoFormat(room.lastChat.lastSentAt)
                )
            )
        }

        data class LastChat(
            var lastMessage: String,
            var lastSentAt: String,
        )
    }
}
