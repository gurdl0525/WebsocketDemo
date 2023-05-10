package com.example.websocketdemo.domain.chat.service.socket

import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.SocketIOServer
import com.example.websocketdemo.domain.chat.controller.dto.request.SendChatRequest

interface SendChatService {

    fun execute(socketIOServer: SocketIOServer, socketIOClient: SocketIOClient, request: SendChatRequest)
}