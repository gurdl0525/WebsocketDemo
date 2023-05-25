package com.example.websocketdemo.global.config.websocket.listener

import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.annotation.OnConnect
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.facade.UserFacade
import com.example.websocketdemo.global.config.websocket.facade.SocketRoomFacade
import com.example.websocketdemo.global.config.websocket.property.ClientProperties
import org.springframework.stereotype.Component


@Component
class SocketConnectListener(
    private val socketRoomFacade: SocketRoomFacade,
    private val userFacade: UserFacade,
){

    @OnConnect
    fun onConnect(socketIOClient: SocketIOClient) {

        val user: User = userFacade.getCurrentUser(socketIOClient)

        socketIOClient.set(ClientProperties.USER_KEY, user.id)

        socketRoomFacade.joinAllRoom(socketIOClient, user)
    }

}