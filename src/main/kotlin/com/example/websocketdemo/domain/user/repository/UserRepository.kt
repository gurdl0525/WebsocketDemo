package com.example.websocketdemo.domain.user.repository

import com.example.websocketdemo.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository: JpaRepository<User, UUID> {

    fun existsByAccountId(accountId: String): Boolean

    fun findByAccountId(accountId: String): User?
}