package com.example.websocketdemo.domain.chat.service

import com.example.websocketdemo.domain.chat.controller.dto.response.ChatResponse
import com.example.websocketdemo.domain.chat.controller.dto.response.QueryChatListResponse
import com.example.websocketdemo.domain.chat.entity.RoomUser
import com.example.websocketdemo.domain.chat.exception.RoomNotFoundException
import com.example.websocketdemo.domain.chat.facade.RoomUserFacade
import com.example.websocketdemo.domain.chat.repository.ChatRepository
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
class QueryChatListServiceImpl(
    private val userFacade: UserFacade,
    private val roomUserFacade: RoomUserFacade,
    private val chatRepository: ChatRepository
): QueryChatListService {

    @Transactional
    override fun execute(roomId: UUID, localDateTime: LocalDateTime): QueryChatListResponse {

        val user: User = userFacade.getCurrentUser()

        val roomUser: RoomUser = roomUserFacade.getById(roomId, user.id!!)

        val chatList = chatRepository.findTopByRoomAndAndCreatedAtBeforeOrderByIdAsc(roomUser.room, localDateTime)
            ?: throw RoomNotFoundException

        roomUser.updateLastReadTime()

        return QueryChatListResponse(
            chatList
                .map { ChatResponse.of(it, it.sender === user) }
                .toList()
        )
    }
}
