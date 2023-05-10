package com.example.websocketdemo.global.config.websocket.listener

import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.listener.ExceptionListener
import com.example.websocketdemo.global.config.websocket.property.SocketProperties
import com.example.websocketdemo.global.error.ErrorCode
import com.example.websocketdemo.global.error.ErrorResponse
import com.example.websocketdemo.global.exception.BusinessException
import io.netty.channel.ChannelHandlerContext
import java.util.*


class SocketExceptionListener: ExceptionListener {

    override fun onEventException(e: Exception, args: MutableList<Any>, client: SocketIOClient) {
        runExceptionHandling(e, client)
    }

    override fun onDisconnectException(e: Exception, client: SocketIOClient) {
        runExceptionHandling(e, client)
    }

    override fun onConnectException(e: Exception, client: SocketIOClient) {
        runExceptionHandling(e, client)
        client.disconnect()
    }

    override fun onPingException(e: Exception, client: SocketIOClient) {
        runExceptionHandling(e, client)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, e: Throwable) = false

    private fun runExceptionHandling(e: Exception, client: SocketIOClient) {

        val errorCode: ErrorCode

        println(e.cause)
        println(e.message)
        println(e.javaClass)
        println(e.cause!!.message)

        Arrays.stream(e.cause!!.stackTrace).forEach(System.out::println)

        if (e.cause is BusinessException) {
            errorCode = (e.cause as BusinessException).errorCode
        } else {
            errorCode = ErrorCode.INTERNAL_SERVER_ERROR
        }

        val message: ErrorResponse = ErrorResponse.of(errorCode)
        client.sendEvent(SocketProperties.ERROR, message)
    }

}
