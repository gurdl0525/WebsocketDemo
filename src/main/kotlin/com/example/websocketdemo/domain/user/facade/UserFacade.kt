package com.example.websocketdemo.domain.user.facade

import com.corundumstudio.socketio.SocketIOClient
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.exception.UserNotFoundException
import com.example.websocketdemo.domain.user.repository.UserRepository
import com.example.websocketdemo.global.config.jwt.JwtTokenParser
import com.example.websocketdemo.global.config.jwt.JwtTokenResolver
import com.example.websocketdemo.global.config.websocket.util.SocketUtil
import com.example.websocketdemo.global.exception.UnAuthorizedException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserFacade(
    private val userRepository: UserRepository,
    private val jwtTokenParser: JwtTokenParser,
    private val jwtTokenResolver: JwtTokenResolver,
){

    fun getCurrentUser(): User {

        val authentication: Authentication = SecurityContextHolder.getContext().authentication
            ?: throw UnAuthorizedException

        val userName: String = authentication.name

        return getUserByAccountId(userName) ?: throw UnAuthorizedException
    }

    fun getCurrentUser(socketIOClient: SocketIOClient) = getUserByAccountId(
        jwtTokenParser.getAuthentication(jwtTokenResolver.resolveToken(socketIOClient)).name
    ) ?: throw UnAuthorizedException

    fun getUserBySocketClient(socketIOClient: SocketIOClient) = userRepository.findByIdOrNull(SocketUtil.getUserId(socketIOClient))
        ?: throw UnAuthorizedException

    fun getUserByAccountId(accountId: String) = userRepository.findByAccountId(accountId)
}