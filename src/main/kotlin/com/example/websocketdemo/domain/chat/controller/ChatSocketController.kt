package com.example.websocketdemo.domain.chat.controller

import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.SocketIOServer
import com.corundumstudio.socketio.annotation.OnEvent
import com.example.websocketdemo.domain.chat.controller.dto.request.JoinSocketRoomRequest
import com.example.websocketdemo.domain.chat.controller.dto.request.SendChatRequest
import com.example.websocketdemo.domain.chat.service.socket.JoinSocketRoomService
import com.example.websocketdemo.domain.chat.service.socket.SendChatService
import com.example.websocketdemo.global.config.websocket.property.SocketProperties
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class ChatSocketController(
    private val socketIOServer: SocketIOServer,
    private val sendChatService: SendChatService,
    private val joinSocketRoomService: JoinSocketRoomService,
) {

    @OnEvent(SocketProperties.CHAT)
    fun sendChat(
        socketIOClient: SocketIOClient,
        @Valid @RequestBody
        request: SendChatRequest
    ) {
        sendChatService.execute(socketIOServer, socketIOClient, request)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @OnEvent(SocketProperties.JOIN)
    fun enterRoom(
        socketIOClient: SocketIOClient,
        @Valid @RequestBody
        request: JoinSocketRoomRequest
    ) {
        joinSocketRoomService.execute(socketIOClient, request)
    }
}