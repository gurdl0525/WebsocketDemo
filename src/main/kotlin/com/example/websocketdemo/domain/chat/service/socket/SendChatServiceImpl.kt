package com.example.websocketdemo.domain.chat.service.socket

import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.SocketIOServer
import com.example.websocketdemo.domain.chat.controller.dto.request.SendChatRequest
import com.example.websocketdemo.domain.chat.controller.dto.response.ChatResponse
import com.example.websocketdemo.domain.chat.entity.Chat
import com.example.websocketdemo.domain.chat.facade.RoomFacade
import com.example.websocketdemo.domain.chat.facade.RoomUserFacade
import com.example.websocketdemo.domain.chat.repository.ChatRepository
import com.example.websocketdemo.domain.user.facade.UserFacade
import com.example.websocketdemo.global.config.websocket.property.SocketProperties
import com.example.websocketdemo.global.config.websocket.util.SocketUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class SendChatServiceImpl(
    private val chatRepository: ChatRepository,
    private val userFacade: UserFacade,
    private val roomFacade: RoomFacade,
    private val roomUserFacade: RoomUserFacade,
): SendChatService {

    @Transactional
    override fun execute(socketIOServer: SocketIOServer, socketIOClient: SocketIOClient, request: SendChatRequest) {

        val user = userFacade.getUserBySocketClient(socketIOClient)
        val room = roomFacade.getCurrentRoom(socketIOClient)

        val roomUser = roomUserFacade.getById(room.id!!, user.id!!)

        val chat: Chat = chatRepository.save(Chat(message = request.message, room = room, sender = user))

        room.updateLastMessage(chat)
        roomUser.updateLastReadTime()

        socketIOServer
            .getRoomOperations(room.id.toString())
            .clients
            .forEach {

                it.sendEvent(SocketProperties.CHAT, ChatResponse.of(chat, it === socketIOClient))

                val clientRoomUser = roomUserFacade.getById(room.id!!, SocketUtil.getUserId(it))

                clientRoomUser.updateLastReadTime()
            }

    }
}