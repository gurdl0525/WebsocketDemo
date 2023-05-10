package com.example.websocketdemo.domain.chat.service

import com.example.websocketdemo.domain.chat.controller.dto.response.QueryRoomListResponse
import com.example.websocketdemo.domain.chat.controller.dto.response.RoomResponse
import com.example.websocketdemo.domain.chat.repository.RoomUserRepository
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.facade.UserFacade
import com.example.websocketdemo.global.exception.RoomUserNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyRoomListServiceImpl(
    private val roomUserRepository: RoomUserRepository,
    private val userFacade: UserFacade,
): QueryMyRoomListService {

    @Transactional(readOnly = true)
    override fun execute(): QueryRoomListResponse {
        val user: User = userFacade.getCurrentUser()

        val roomUserList  = roomUserRepository.findAllByUser(user)
            ?: throw RoomUserNotFoundException

        return QueryRoomListResponse(
            roomUserList
                .map(RoomResponse::of)
                .sortedBy { it.lastChat.lastSentAt }
                .toList()
        )
    }
}
