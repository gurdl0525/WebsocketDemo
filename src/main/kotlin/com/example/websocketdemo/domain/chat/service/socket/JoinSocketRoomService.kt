package com.example.websocketdemo.domain.chat.service.socket

import com.corundumstudio.socketio.SocketIOClient
import com.example.websocketdemo.domain.chat.controller.dto.request.JoinSocketRoomRequest

interface JoinSocketRoomService {

    fun execute(socketIOClient: SocketIOClient, request: JoinSocketRoomRequest)
}