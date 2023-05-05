package com.example.websocketdemo.domain.auth.service

import com.example.websocketdemo.domain.auth.controller.dto.SignUpRequest
import com.example.websocketdemo.domain.auth.exception.DuplicatedAccountIdException
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
): AuthService {

    override fun signUp(req: SignUpRequest) {

        if(userRepository.existsByAccountId(req.accountId)){
            throw DuplicatedAccountIdException
        }

        userRepository.save(
            User(
                accountId = req.accountId,
                password = passwordEncoder.encode(req.password)
            )
        )
    }
}