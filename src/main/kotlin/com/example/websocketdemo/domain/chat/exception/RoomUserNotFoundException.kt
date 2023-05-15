package com.example.websocketdemo.domain.chat.exception

import com.example.websocketdemo.global.error.ErrorCode
import com.example.websocketdemo.global.exception.BusinessException

object RoomUserNotFoundException: BusinessException(ErrorCode.ROOM_USER_NOT_FOUND)