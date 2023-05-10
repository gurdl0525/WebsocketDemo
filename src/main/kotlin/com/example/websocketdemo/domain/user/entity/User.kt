package com.example.websocketdemo.domain.user.entity

import com.example.websocketdemo.domain.chat.entity.RoomUser
import java.util.*
import javax.persistence.*

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

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var roomUser: MutableList<RoomUser> = ArrayList()
        protected set
}