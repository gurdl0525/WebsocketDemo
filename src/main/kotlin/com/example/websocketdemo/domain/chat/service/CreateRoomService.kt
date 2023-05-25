package com.example.websocketdemo.domain.chat.service

import com.example.websocketdemo.domain.chat.controller.dto.request.CreateRoomRequest
import com.example.websocketdemo.domain.chat.controller.dto.response.CreateRoomResponse

interface CreateRoomService {

    fun exec(req: CreateRoomRequest): CreateRoomResponse
}
