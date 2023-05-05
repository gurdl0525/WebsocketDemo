package com.example.websocketdemo.global.common

import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.exception.UserNotFoundException
import com.example.websocketdemo.domain.user.repository.UserRepository
import com.example.websocketdemo.global.config.security.principal.AuthDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
){
    
    fun getUserByToken(): User {

        val authDetails = (SecurityContextHolder.getContext().authentication
            ?: throw UserNotFoundException).principal as AuthDetails

        return userRepository.findByAccountId(authDetails.username)!!
    }
}
