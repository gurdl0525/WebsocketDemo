package com.example.websocketdemo.domain.chat.entity

import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.global.util.DateUtil
import org.springframework.data.domain.Persistable
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@IdClass(RoomUser.RoomUserIdClass::class)
@Entity(name = "room_user")
class RoomUser(
    user: User,
    room: Room,
): Persistable<RoomUser.RoomUserIdClass> {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", nullable = false)
    var user: User = user
        protected set

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room", nullable = false)
    var room: Room = room
        protected set

    fun updateLastReadTime() {
        this.lastRead = DateUtil.getZonedNow()
    }

    @Column(name = "last_read",nullable = false)
    var lastRead: LocalDateTime = DateUtil.getZonedNow()

    data class RoomUserIdClass(
        var room: UUID? = null,
        var user: UUID? = null
    ) : Serializable

    override fun getId() = RoomUserIdClass(user.id, room.id)

    override fun isNew() = false

}