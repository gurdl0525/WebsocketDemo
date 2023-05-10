package com.example.websocketdemo.global.exception

import com.example.websocketdemo.global.error.ErrorCode

object InvalidUserException: BusinessException(ErrorCode.INVALID_USER)
