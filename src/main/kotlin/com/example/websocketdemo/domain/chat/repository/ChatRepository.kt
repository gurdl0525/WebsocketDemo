package com.example.websocketdemo.domain.chat.repository

import com.example.websocketdemo.domain.chat.entity.Chat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ChatRepository: JpaRepository<Chat, UUID> {
}