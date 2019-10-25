package com.example.player.domain

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PlayerService(val playerRepository: PlayerRepository) {

    val log = LoggerFactory.getLogger(this::class.java)

    fun create(name: String): Player {
        val player = Player(name = name)
        log.info("Saving $player")
        val savedPlayer = playerRepository.save(player)
        log.info("Saved $savedPlayer")
        return savedPlayer
    }

    fun get(id: String): Player {
        log.info("Getting player=[$id]")
        val player = playerRepository.findById(id)
        log.info("Got player=[$id]")
        return player.orElseThrow({ RuntimeException("Player=[$id] was not found") })
    }

    fun get(): MutableIterable<Player> {
        log.info("Getting all players")
        val players = playerRepository.findAll()
        log.info("Got all players")
        return players;
    }

    fun delete(id: String) {
        log.info("Deleting Player=[$id]")
        playerRepository.deleteById(id)
        log.info("Deleted Player=[$id]")
    }

}