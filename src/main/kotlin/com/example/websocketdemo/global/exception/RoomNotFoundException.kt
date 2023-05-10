package com.example.websocketdemo.global.exception

import com.example.websocketdemo.global.error.ErrorCode

object RoomNotFoundException: BusinessException(ErrorCode.ROOM_NOT_FOUND)
