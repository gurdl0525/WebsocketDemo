package com.example.websocketdemo.global.config.security.principal

import com.example.websocketdemo.domain.user.repository.UserRepository
import com.example.websocketdemo.global.exception.UnAuthorizedException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AuthDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(accountId: String): UserDetails {
        return AuthDetails(userRepository.findByAccountId(accountId)
            ?: throw UnAuthorizedException
        )
    }
}