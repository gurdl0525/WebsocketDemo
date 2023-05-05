package com.example.websocketdemo.domain.user.exception

import com.example.websocketdemo.global.error.ErrorCode
import com.example.websocketdemo.global.exception.BusinessException

object UserNotFoundException : BusinessException(ErrorCode.USER_NOT_FOUND)
