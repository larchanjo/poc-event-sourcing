package com.example.player.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class WrapperPlayerRepository(val playerRepository: PlayerRepository,
                              val playerEventService: PlayerEventService
) : CrudRepository<Player, String> {

    override fun <S : Player?> save(player: S): S {
        val savedPlayer = playerRepository.save(player)
        if (savedPlayer != null) {
            playerEventService.save(savedPlayer.id, savedPlayer.name, savedPlayer.score)
        }
        return savedPlayer
    }

    override fun findAll(): MutableIterable<Player> {
        return playerRepository.findAll()
    }

    override fun deleteById(id: String) {
        playerRepository.deleteById(id)
    }

    override fun deleteAll(players: MutableIterable<Player>) {
        playerRepository.deleteAll(players)
    }

    override fun deleteAll() {
        playerRepository.deleteAll()
    }

    override fun <S : Player?> saveAll(players: MutableIterable<S>): MutableIterable<S> {
        return playerRepository.saveAll(players)
    }

    override fun count(): Long {
        return playerRepository.count()
    }

    override fun findAllById(ids: MutableIterable<String>): MutableIterable<Player> {
        return playerRepository.findAllById(ids)
    }

    override fun existsById(id: String): Boolean {
        return playerRepository.existsById(id)
    }

    override fun findById(id: String): Optional<Player> {
        return playerRepository.findById(id)
    }

    override fun delete(player: Player) {
        playerRepository.delete(player)
    }

}