package com.example.websocketdemo.domain.chat.service

import com.example.websocketdemo.domain.chat.controller.dto.response.QueryChatListResponse
import java.time.LocalDateTime
import java.util.*

interface QueryChatListService {

    fun execute(roomId: UUID, localDateTime: LocalDateTime): QueryChatListResponse
}
