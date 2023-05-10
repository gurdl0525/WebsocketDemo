package com.example.websocketdemo.domain.chat.facade

import com.example.websocketdemo.domain.chat.entity.Room
import com.example.websocketdemo.domain.chat.entity.RoomUser
import com.example.websocketdemo.domain.chat.repository.RoomUserRepository
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.global.exception.RoomUserNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class RoomUserFacade(
    private val roomUserRepository: RoomUserRepository
) {

    fun getById(roomId: UUID, userId: UUID) = roomUserRepository.findByIdOrNull(RoomUser.RoomUserIdClass(roomId, userId))
        ?: throw RoomUserNotFoundException

    fun getById(roomUserId: RoomUser.RoomUserIdClass) = roomUserRepository.findByIdOrNull(roomUserId)
        ?: throw RoomUserNotFoundException

    fun getListByUser(user: User): List<RoomUser>? = roomUserRepository.findAllByUser(user)

    fun checkRoomUserExist(room: Room, user: User) {
        roomUserRepository.findByIdOrNull(RoomUser.RoomUserIdClass(room.id, user.id)) ?: throw RoomUserNotFoundException
    }
}