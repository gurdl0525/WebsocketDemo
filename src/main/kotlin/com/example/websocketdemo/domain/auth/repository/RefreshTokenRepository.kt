package com.example.websocketdemo.domain.auth.repository

import com.example.websocketdemo.domain.auth.entity.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository: CrudRepository<RefreshToken, String> {

    fun findByToken(token: String): RefreshToken?
}