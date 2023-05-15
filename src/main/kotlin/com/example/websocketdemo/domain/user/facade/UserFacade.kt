package com.example.websocketdemo.domain.user.facade

import com.corundumstudio.socketio.SocketIOClient
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.exception.UserNotFoundException
import com.example.websocketdemo.domain.user.repository.UserRepository
import com.example.websocketdemo.global.config.websocket.util.SocketUtil
import com.example.websocketdemo.global.exception.UnAuthorizedException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
){

    fun getCurrentUser(): User {

        val authentication: Authentication = SecurityContextHolder.getContext().authentication

        val userName: String = authentication.name

        return findByAccountId(userName) ?: throw UnAuthorizedException
    }

    fun getCurrentUser(socketIOClient: SocketIOClient) = userRepository.findByIdOrNull(SocketUtil.getUserId(socketIOClient))
        ?: throw UnAuthorizedException

    fun getUserByAccountId(accountId: String) = findByAccountId(accountId) ?: throw UserNotFoundException

    private fun findByAccountId(accountId: String) = userRepository.findByAccountId(accountId)
}