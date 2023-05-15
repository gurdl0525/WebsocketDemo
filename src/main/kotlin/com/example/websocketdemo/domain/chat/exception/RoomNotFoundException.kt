package com.example.websocketdemo.domain.chat.exception

import com.example.websocketdemo.global.error.ErrorCode
import com.example.websocketdemo.global.exception.BusinessException

object RoomNotFoundException: BusinessException(ErrorCode.ROOM_NOT_FOUND)