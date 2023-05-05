package com.example.websocketdemo.global.exception

import com.example.websocketdemo.global.error.ErrorCode

object ExpiredTokenException: BusinessException(ErrorCode.EXPIRED_TOKEN)
