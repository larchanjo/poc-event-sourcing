package com.example.player.domain

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Player(val id: String = UUID.randomUUID().toString(), var name: String, var score: Int = 0)