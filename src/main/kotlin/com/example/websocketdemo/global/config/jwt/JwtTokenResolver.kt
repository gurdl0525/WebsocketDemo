package com.example.websocketdemo.global.config.jwt

import com.corundumstudio.socketio.SocketIOClient
import com.example.websocketdemo.global.exception.UnAuthorizedException
import org.springframework.stereotype.Component
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenResolver {

    fun resolveToken(httpServletRequest: HttpServletRequest): String? = parseToken(
        httpServletRequest.getHeader("Authorization")
    )

    fun resolveToken(socketIOClient: SocketIOClient): String {

        return parseToken(socketIOClient.handshakeData.urlParams["auth"])
            ?: throw UnAuthorizedException
    }

    private fun parseToken(token: List<String>?): String? =
        if (!token.isNullOrEmpty() && Pattern.matches("[(a-zA-Z0-9-._~+/=*)]{30,600}", token[0])) {
            token[0]
        } else {
            null
        }

    private fun parseToken(token: String?) =
        if (token != null && Pattern.matches("Bearer [(a-zA-Z0-9-._~+/=*)]{30,600}", token)) {
            token.substring(7)
        } else {
            null
        }
}