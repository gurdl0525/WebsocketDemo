package com.example.websocketdemo.global.exception

import com.example.websocketdemo.global.error.ErrorCode

open class BusinessException(
    val errorCode: ErrorCode
) : RuntimeException() {

    override fun fillInStackTrace() = this
}
