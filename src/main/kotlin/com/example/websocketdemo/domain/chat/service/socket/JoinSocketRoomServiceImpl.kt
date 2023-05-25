package com.example.websocketdemo.domain.chat.service.socket

import com.corundumstudio.socketio.SocketIOClient
import com.example.websocketdemo.domain.chat.controller.dto.request.JoinSocketRoomRequest
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.facade.UserFacade
import com.example.websocketdemo.global.config.websocket.facade.SocketRoomFacade
import org.springframework.stereotype.Service

@Service
class JoinSocketRoomServiceImpl(
    private val userFacade: UserFacade,
    private val socketRoomFacade: SocketRoomFacade,
): JoinSocketRoomService {

    override fun execute(socketIOClient: SocketIOClient, request: JoinSocketRoomRequest) {

        val user: User = userFacade.getUserBySocketClient(socketIOClient)

        if (request.isJoinRoom) {
            val roomId = request.roomId
            socketRoomFacade.joinOneRoom(socketIOClient, user, roomId)
        } else {
            socketRoomFacade.joinAllRoom(socketIOClient, user)
        }
    }
}