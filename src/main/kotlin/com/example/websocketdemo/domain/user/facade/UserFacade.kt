package com.example.websocketdemo.domain.user.facade

import com.corundumstudio.socketio.SocketIOClient
import com.example.websocketdemo.domain.auth.exception.PasswordMismatchedException
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.exception.UserNotFoundException
import com.example.websocketdemo.domain.user.repository.UserRepository
import com.example.websocketdemo.global.config.security.principal.AuthDetails
import com.example.websocketdemo.global.config.websocket.util.SocketUtil
import com.example.websocketdemo.global.exception.UnAuthorizedException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserFacade(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
){

    fun getCurrentUser(): User {

        val authDetails = (SecurityContextHolder.getContext().authentication
            ?: throw UnAuthorizedException).principal as AuthDetails

        return userRepository.findByAccountId(authDetails.username)!!
    }

    fun getCurrentUser(socketIOClient: SocketIOClient) = userRepository.findByIdOrNull(SocketUtil.getUserId(socketIOClient))
        ?: throw UnAuthorizedException

    fun getUserByAccountId(accountId: String) = userRepository.findByAccountId(accountId) ?: throw UserNotFoundException

    fun getUserById(id: UUID) = userRepository.findByIdOrNull(id) ?: throw UserNotFoundException

    fun checkPassword(user: User, password: String?) {
        if (!passwordEncoder.matches(password, user.password)) {
            throw PasswordMismatchedException
        }
    }
}