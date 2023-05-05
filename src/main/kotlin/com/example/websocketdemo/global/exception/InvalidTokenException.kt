package com.example.websocketdemo.global.exception

import com.example.websocketdemo.global.error.ErrorCode

object InvalidTokenException: BusinessException(ErrorCode.INVALID_TOKEN)
