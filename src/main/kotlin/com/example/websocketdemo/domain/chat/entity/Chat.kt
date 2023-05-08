package com.example.websocketdemo.domain.chat.entity

import com.example.websocketdemo.domain.user.entity.User
import java.util.*
import javax.persistence.*

@Entity
class Chat(
    id: UUID? = null,
    content: String,
    sender: String,
    target: User,
) {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = id
        protected set

    @Column(name = "content", length = 1000, nullable = false)
    var content:String = content
        protected set

    @Column(name = "sender", length = 15, nullable = false)
    var sender: String = sender
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target", nullable = false)
    var target: User = target
}