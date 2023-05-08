package com.example.websocketdemo.domain.chat.controller

import com.example.websocketdemo.domain.chat.entity.Chat
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import javax.websocket.OnMessage
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.server.ServerEndpoint


@RestController
@ServerEndpoint("/websocket")
class MessageController {

    private val session: MutableList<Session> = ArrayList()

    @GetMapping("/")
    fun index(): String = "index.html"

    @OnOpen
    fun open(newUser: Session) {
        println("connected")
        session.add(newUser)
        println(newUser.id)
    }

    @OnMessage
    fun getMsg(receiveSession: Session, msg: String) {
        for (i in session.indices) {
            if (receiveSession.id != session[i].id) {
                try {
                    session[i].basicRemote.sendText("상대 : $msg")
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                try {
                    session[i].basicRemote.sendText("나 : $msg")
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    /*
    @MessageMapping("/hello")
    @SendTo("/topic/public")
    fun chatting(@Payload chat: Chat): Chat = Chat(chat.id, chat.content + "hello", chat.sender)

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    fun addUser(@Payload chat: Chat, headerAccessor: SimpMessageHeaderAccessor): Chat {
    headerAccessor.sessionAttributes!!["username"] = chat.sender
    return chat
    }
    */
}