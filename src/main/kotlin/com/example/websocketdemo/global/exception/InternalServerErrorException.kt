package com.example.websocketdemo.global.exception

import com.example.websocketdemo.global.error.ErrorCode

object InternalServerErrorException: BusinessException(ErrorCode.INTERNAL_SERVER_GLOBAL_ERROR)
