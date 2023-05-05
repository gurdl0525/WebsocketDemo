package com.example.websocketdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class WebsocketDemoApplication

fun main(args: Array<String>) {
    runApplication<WebsocketDemoApplication>(*args)
}
