package com.example.websocketdemo.global.config.jwt.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    secretKey: String,
    accessExp: Int,
    refreshExp: Int
) {
    val secretKey: String = secretKey
    val accessExp: Int = accessExp * 1000
    val refreshExp: Int = refreshExp * 1000
}