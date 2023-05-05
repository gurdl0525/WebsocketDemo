package com.example.websocketdemo.domain.auth.service

import com.example.websocketdemo.domain.auth.controller.dto.request.LoginRequest
import com.example.websocketdemo.domain.auth.controller.dto.request.SignUpRequest
import com.example.websocketdemo.domain.auth.controller.dto.response.TokenResponse
import com.example.websocketdemo.domain.auth.exception.DuplicatedAccountIdException
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.exception.UserNotFoundException
import com.example.websocketdemo.domain.user.repository.UserRepository
import com.example.websocketdemo.global.common.UserFacade
import com.example.websocketdemo.global.config.jwt.GenerateJwtAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtProvider: GenerateJwtAdapter,
    private val userFacade: UserFacade
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

    override fun login(req: LoginRequest) =
        if(userRepository.existsByAccountId(req.accountId)) {
            jwtProvider.receiveToken(req.accountId)
        }else{
            throw UserNotFoundException
        }

    override fun reissue() = jwtProvider.receiveToken(userFacade.getUserByToken().accountId)
}