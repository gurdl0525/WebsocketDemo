package com.example.websocketdemo.domain.chat.service

import com.example.websocketdemo.domain.chat.controller.dto.response.QueryRoomListResponse

interface QueryMyRoomListService {

    fun execute(): QueryRoomListResponse
}
