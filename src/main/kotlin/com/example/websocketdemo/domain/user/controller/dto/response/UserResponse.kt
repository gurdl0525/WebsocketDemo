package com.example.websocketdemo.domain.user.controller.dto.response

import com.example.websocketdemo.domain.user.entity.User
import java.util.*

data class UserResponse(

    val userId: UUID,

    val userName: String
) {
    companion object{
        fun of(user: User): UserResponse {
            return UserResponse(user.id!!, user.accountId)
        }
    }
}
