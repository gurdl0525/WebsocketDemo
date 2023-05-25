package com.example.websocketdemo.domain.chat.service

import com.example.websocketdemo.domain.chat.controller.dto.request.CreateRoomRequest
import com.example.websocketdemo.domain.chat.controller.dto.response.CreateRoomResponse
import com.example.websocketdemo.domain.chat.entity.Room
import com.example.websocketdemo.domain.chat.repository.RoomRepository
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.exception.UserNotFoundException
import com.example.websocketdemo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CreateRoomServiceImpl(
    private val roomRepository: RoomRepository,
    private val userFacade: UserFacade,
): CreateRoomService {

    @Transactional
    override fun exec(req: CreateRoomRequest): CreateRoomResponse {

        val sender: User = userFacade.getCurrentUser()
        val acceptor: User = userFacade.getUserByAccountId(req.accountId!!) ?: throw UserNotFoundException

        val room: Room = roomRepository.save(Room())

        room.addRoomUser(sender)
        room.addRoomUser(acceptor)

        return CreateRoomResponse(
            room.id!!
        )
    }
}