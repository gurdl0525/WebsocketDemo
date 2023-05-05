package com.example.websocketdemo.domain.auth.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(timeToLive = 60 * 60 * 4)
data class RefreshToken(

    @Id
    val accountId: String,

    @Indexed
    val token: String
)