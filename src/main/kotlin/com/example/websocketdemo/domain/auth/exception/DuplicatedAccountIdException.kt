package com.example.websocketdemo.domain.auth.exception

import com.example.websocketdemo.global.error.ErrorCode
import com.example.websocketdemo.global.exception.BusinessException

object DuplicatedAccountIdException : BusinessException(ErrorCode.DUPLICATED_ACCOUNT_ID)
