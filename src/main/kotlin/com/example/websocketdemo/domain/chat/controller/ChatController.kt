package com.example.websocketdemo.domain.chat.controller

import com.example.websocketdemo.domain.chat.controller.dto.request.CreateRoomRequest
import com.example.websocketdemo.domain.chat.controller.dto.response.CreateRoomResponse
import com.example.websocketdemo.domain.chat.controller.dto.response.QueryChatListResponse
import com.example.websocketdemo.domain.chat.controller.dto.response.QueryRoomListResponse
import com.example.websocketdemo.domain.chat.service.CreateRoomService
import com.example.websocketdemo.domain.chat.service.QueryChatListService
import com.example.websocketdemo.domain.chat.service.QueryMyRoomListService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*
import javax.validation.Valid


@RequestMapping("/chat")
@RestController
class ChatController(
    private val queryChatListService: QueryChatListService,
    private val queryMyRoomListService: QueryMyRoomListService,
    private val createRoomService: CreateRoomService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/room")
    fun createPrivateChatRoom(
        @RequestBody @Valid
        request: CreateRoomRequest
    ): CreateRoomResponse = createRoomService.exec(request)

    @GetMapping("/{room-id}")
    fun queryChatList(
        @PathVariable("room-id") roomId: UUID,
        @RequestParam("time") localDateTime: LocalDateTime
    ): QueryChatListResponse = queryChatListService.execute(roomId, localDateTime)

    @GetMapping("/room")
    fun queryMyRoomList(): QueryRoomListResponse = queryMyRoomListService.execute()
}