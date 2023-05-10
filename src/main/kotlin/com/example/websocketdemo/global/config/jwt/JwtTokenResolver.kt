package com.example.websocketdemo.global.config.jwt

import com.corundumstudio.socketio.SocketIOClient
import org.springframework.stereotype.Component
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenResolver {

    fun resolveToken(httpServletRequest: HttpServletRequest) = parseToken(
        httpServletRequest.getHeader("Authorization")
    )

    fun resolveToken(socketIOClient: SocketIOClient) = parseToken(
        socketIOClient.handshakeData.httpHeaders["Authorization"]
    )

    private fun parseToken(token: String?) =
        if (token != null && Pattern.matches("Bearer [(a-zA-Z0-9-._~+/=*)]{30,600}", token)) {
            token.substring(7)
        } else {
            null
        }
}