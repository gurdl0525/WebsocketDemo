package com.example.websocketdemo.domain.chat.repository

import com.example.websocketdemo.domain.chat.entity.Room
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoomRepository: JpaRepository<Room, UUID> {
}
