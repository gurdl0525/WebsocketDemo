package com.example.websocketdemo.domain.chat.entity

import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.domain.user.exception.UserNotFoundException
import com.example.websocketdemo.global.util.DateUtil
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*


@Entity(name = "room")
class Room(
    id: UUID? = null,
    roomUsers: MutableList<RoomUser> = ArrayList(),
) {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: UUID? = id
        protected set

    @OneToMany(mappedBy = "room", cascade = [CascadeType.PERSIST], orphanRemoval = true)
    var roomUsers: MutableList<RoomUser> = roomUsers
        protected set

    @Embedded
    @Column(nullable = false)
    var lastChat: LastChat = LastChat()
        protected set

    fun updateLastMessage(chat: Chat) {
        lastChat.lastMessage = chat.message
        lastChat.lastSentAt = chat.createdAt
    }

    fun getOtherUser(user: User): User {
        if (roomUsers.size < 2) {
            throw UserNotFoundException
        }
        val user1: User = roomUsers[0].user
        val user2: User = roomUsers[1].user
        return if (user1 !== user) user1 else user2
    }

    @Embeddable
    class LastChat {
        var lastMessage = ""
        var lastSentAt: LocalDateTime = DateUtil.getZonedNow()
    }


}
