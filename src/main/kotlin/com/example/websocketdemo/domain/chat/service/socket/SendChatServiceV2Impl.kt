package com.example.websocketdemo.domain.chat.service.socket

import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.SocketIOServer
import com.example.websocketdemo.domain.chat.controller.dto.request.SendChatRequest
import com.example.websocketdemo.domain.chat.controller.dto.response.ChatResponse
import com.example.websocketdemo.domain.chat.entity.Chat
import com.example.websocketdemo.domain.chat.entity.Room
import com.example.websocketdemo.domain.chat.entity.RoomUser
import com.example.websocketdemo.domain.chat.facade.RoomFacade
import com.example.websocketdemo.domain.chat.facade.RoomUserFacade
import com.example.websocketdemo.domain.chat.repository.ChatRepository
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.facade.UserFacade
import com.example.websocketdemo.global.config.websocket.property.SocketProperties
import com.example.websocketdemo.global.config.websocket.util.SocketUtil
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class SendChatServiceV2Impl(
    private val chatRepository: ChatRepository,
    private val userFacade: UserFacade,
    private val roomFacade: RoomFacade,
    private val roomUserFacade: RoomUserFacade,
): SendChatServiceV2 {

    @Transactional
    override fun execute(socketIOServer: SocketIOServer, socketIOClient: SocketIOClient, request: SendChatRequest) {

        println("SendChatServiceV2.execute")

        val user: User = userFacade.getCurrentUser(socketIOClient)
        val room: Room = roomFacade.getCurrentRoom(socketIOClient)

        val roomUser: RoomUser = roomUserFacade.getById(room.id!!, user.id!!)

        val chat: Chat = chatRepository.save(Chat(message = request.message, room = room, sender = user))

        room.updateLastMessage(chat)
        roomUser.updateLastReadTime()

        val mapper = ObjectMapper()

        socketIOServer.getRoomOperations(room.id.toString())
            .clients
            .forEach {
                try {
                    it.sendEvent(
                        SocketProperties.CHAT,
                        mapper.writeValueAsString(ChatResponse.of(chat, it === socketIOClient))
                    )
                } catch (e: JsonProcessingException) {
                    println("jsonProcessingException")
                }
                val clientRoomUser: RoomUser = roomUserFacade
                    .getById(room.id!!, SocketUtil.getUserId(it))
                clientRoomUser.updateLastReadTime()
            }
    }
}