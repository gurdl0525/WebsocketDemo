package com.example.websocketdemo.global.config.jwt

import com.example.websocketdemo.domain.auth.controller.dto.response.TokenResponse
import com.example.websocketdemo.domain.auth.entity.RefreshToken
import com.example.websocketdemo.domain.auth.repository.RefreshTokenRepository
import com.example.websocketdemo.global.config.jwt.property.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateJwtAdapter(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {


    fun receiveToken(accountId: String) = TokenResponse(
        generateAccessToken(accountId),
        generateRefreshToken(accountId)
    )


    private fun generateRefreshToken(accountId: String): String {

        val refreshToken = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.refreshExp))
            .compact()

        refreshTokenRepository.save(
            RefreshToken(
                accountId,
                refreshToken
            )
        )

        return refreshToken
    }

    private fun generateAccessToken(accountId: String): String = Jwts.builder()
        .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
        .setSubject(accountId)
        .setIssuedAt(Date())
        .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp))
        .compact()

}