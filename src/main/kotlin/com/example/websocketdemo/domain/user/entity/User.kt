package com.example.websocketdemo.domain.user.entity

import com.example.websocketdemo.domain.chat.entity.Chat
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity(name = "user")
class User(
    id: UUID? = null,
    accountId: String,
    password: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = id
        protected set

    @Column(name = "account_id", length = 20, unique = true, nullable = false)
    var accountId: String = accountId
        protected set

    @Column(name = "password", length = 60, nullable = false)
    var password: String = password
        protected set

    @OneToMany(mappedBy = "target", cascade = [CascadeType.REMOVE])
    var chatList: MutableList<Chat> = ArrayList()
        protected set
}