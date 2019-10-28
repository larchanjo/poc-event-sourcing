package com.example.player

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class PlayerApplication

fun main(args: Array<String>) {
	runApplication<PlayerApplication>(*args)
}
