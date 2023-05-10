package com.example.websocketdemo.global.config.websocket

import com.corundumstudio.socketio.SocketIOServer
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner
import com.example.websocketdemo.global.config.websocket.listener.SocketExceptionListener
import com.example.websocketdemo.global.config.websocket.property.SocketProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebSocketConfig(
    private val property: SocketProperty
) {

    @Bean
    fun socketIOServer(): SocketIOServer {

        val socketConfig = com.corundumstudio.socketio.SocketConfig()
        socketConfig.isReuseAddress = true

        val configuration = com.corundumstudio.socketio.Configuration();
        configuration.port = property.port
        configuration.origin = "*";
        configuration.socketConfig = socketConfig;
        configuration.exceptionListener = SocketExceptionListener();

        return SocketIOServer(configuration);
    }

    @Bean
    fun springAnnotationScanner(socketIOServer: SocketIOServer) = SpringAnnotationScanner(socketIOServer)

}