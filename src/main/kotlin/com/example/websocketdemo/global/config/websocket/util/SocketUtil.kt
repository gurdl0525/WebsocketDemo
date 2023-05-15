package com.example.websocketdemo.global.config.websocket.util

import com.corundumstudio.socketio.SocketIOClient
import com.example.websocketdemo.global.config.websocket.property.ClientProperties
import com.example.websocketdemo.global.exception.InvalidUserException
import com.example.websocketdemo.domain.chat.exception.RoomNotFoundException
import java.util.UUID


object SocketUtil {

    fun getUserId(socketIOClient: SocketIOClient): UUID {
        if (!socketIOClient.has(ClientProperties.USER_KEY)) {
            throw InvalidUserException
        }
        return socketIOClient.get(ClientProperties.USER_KEY)
    }

    fun getRoomId(socketIOClient: SocketIOClient): UUID {
        if (!socketIOClient.has(ClientProperties.ROOM_KEY)) {
            throw RoomNotFoundException
        }
        return socketIOClient.get(ClientProperties.ROOM_KEY)
    }
}