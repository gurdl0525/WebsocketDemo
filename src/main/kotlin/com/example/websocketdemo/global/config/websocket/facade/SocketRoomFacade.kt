package com.example.websocketdemo.global.config.websocket.facade

import com.corundumstudio.socketio.SocketIOClient
import com.example.websocketdemo.domain.chat.entity.Room
import com.example.websocketdemo.domain.chat.exception.RoomUserNotFoundException
import com.example.websocketdemo.domain.chat.facade.RoomFacade
import com.example.websocketdemo.domain.chat.facade.RoomUserFacade
import com.example.websocketdemo.domain.user.entity.User
import com.example.websocketdemo.global.config.websocket.property.ClientProperties
import org.springframework.stereotype.Component
import java.lang.String
import java.util.UUID
import java.util.function.Consumer


@Component
class SocketRoomFacade(
    private val roomFacade: RoomFacade,
    private val roomUserFacade: RoomUserFacade,
) {

    fun joinAllRoom(socketIOClient: SocketIOClient, user: User) {

        val roomUserList = roomUserFacade.getListByUser(user)

        if (roomUserList.isNullOrEmpty()) throw RoomUserNotFoundException

        roomUserList
            .map { socketIOClient.joinRoom(String.valueOf(it.room.id)) }
            .toList()
    }

    fun joinOneRoom(socketIOClient: SocketIOClient, user: User, roomId: UUID) {

        val room: Room = roomFacade.getRoomById(roomId)

        roomUserFacade.checkRoomUserExist(room, user)

        socketIOClient.let {
            it.allRooms.forEach { room ->  socketIOClient.leaveRoom((room)) }
            it[ClientProperties.ROOM_KEY] = roomId
            it.joinRoom(String.valueOf(roomId))
        }
    }
}
