package com.example.websocketdemo.domain.chat.repository

import com.example.websocketdemo.domain.chat.entity.RoomUser
import com.example.websocketdemo.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomUserRepository: JpaRepository<RoomUser, RoomUser.RoomUserIdClass> {

    fun findByUser(user: User): MutableList<RoomUser>
}
