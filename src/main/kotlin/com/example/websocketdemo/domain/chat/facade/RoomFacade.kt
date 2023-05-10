package com.example.websocketdemo.domain.chat.facade

import com.corundumstudio.socketio.SocketIOClient
import com.example.websocketdemo.domain.chat.entity.Room
import com.example.websocketdemo.domain.chat.repository.RoomRepository
import com.example.websocketdemo.global.config.websocket.util.SocketUtil
import com.example.websocketdemo.global.exception.RoomNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*


@Component
class RoomFacade(
    private val roomRepository: RoomRepository
) {

    fun getRoomById(id: UUID): Room {
        return roomRepository.findByIdOrNull(id)
            ?: throw RoomNotFoundException
    }

    fun getCurrentRoom(socketIOClient: SocketIOClient) = getRoomById(SocketUtil.getRoomId(socketIOClient))
}