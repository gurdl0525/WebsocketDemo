package com.example.websocketdemo.domain.chat.repository

import com.example.websocketdemo.domain.chat.entity.Chat
import com.example.websocketdemo.domain.chat.entity.Room
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.UUID

@Repository
interface ChatRepository: JpaRepository<Chat, UUID> {

    fun findTopByRoomAndAndCreatedAtBeforeOrderByIdAsc(room: Room, localDateTime: LocalDateTime): List<Chat>?

}