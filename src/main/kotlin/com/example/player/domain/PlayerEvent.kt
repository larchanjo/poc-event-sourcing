package com.example.player.domain

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Document
data class PlayerEvent(
        val id: String = UUID.randomUUID().toString(),
        val playerId: String,
        val name: String,
        val score: Int = 0,
        val at: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC"))
)