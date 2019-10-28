package com.example.player.domain

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils

@Service
class PlayerService(val repository: PlayerRepository) {

    val log = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun create(name: String): Player {
        val player = Player(name = name)
        log.info("Saving $player")
        val savedPlayer = repository.save(player)
        log.info("Saved $savedPlayer")
        return savedPlayer
    }

    @Transactional
    fun update(id: String, score: Int) {
        log.info("Updating player=[$id]")
        val optional = repository.findById(id)
        if (optional.isPresent) {
            val player = optional.get()
            if (!StringUtils.isEmpty(score)) {
                player.score += score
            }
            repository.save(player)
        }

        log.info("Updated player=[$id]")
    }

    @Transactional(readOnly = true)
    fun get(id: String): Player {
        log.info("Getting player=[$id]")
        val player = repository.findById(id)
        log.info("Got player=[$id]")
        return player.orElseThrow({ RuntimeException("Player=[$id] was not found") })
    }

    @Transactional(readOnly = true)
    fun get(): MutableIterable<Player> {
        log.info("Getting all players")
        val players = repository.findAll()
        log.info("Got all players")
        return players;
    }

    @Transactional
    fun delete(id: String) {
        log.info("Deleting Player=[$id]")
        repository.deleteById(id)
        log.info("Deleted Player=[$id]")
    }

}