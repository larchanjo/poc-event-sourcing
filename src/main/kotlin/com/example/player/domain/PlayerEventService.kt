package com.example.player.domain

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlayerEventService(val repository: PlayerEventRepository) {

    val log = LoggerFactory.getLogger(this::class.java)

    @Async
    @Transactional
    fun save(id: String, name: String, score: Int = 0) {
        val playerEvent = PlayerEvent(playerId = id, name = name, score = score)
        log.info("Saving $playerEvent")
        repository.save(playerEvent)
        log.info("Saved $playerEvent")
    }

    fun get(id: String): Collection<Player> {
        log.info("Finding events by player=[$id]")
        return repository.findByPlayerId(id)
    }

}