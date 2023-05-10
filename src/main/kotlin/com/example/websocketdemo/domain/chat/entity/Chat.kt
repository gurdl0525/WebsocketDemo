package com.example.websocketdemo.domain.chat.entity

import com.example.websocketdemo.domain.user.entity.User
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class Chat(
    id: UUID? = null,
    message: String,
    sender: User,
    room: Room,
    updatedAt: LocalDateTime? = null
) {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: UUID? = id
        protected set

    @Column(name = "message", length = 1000, nullable = false)
    var message:String = message
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender", nullable = false)
    var sender: User = sender
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room", nullable = false)
    var room: Room = room
        protected set

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @Column(name = "updated_at", nullable = true)
    var updatedAt: LocalDateTime? = updatedAt
}