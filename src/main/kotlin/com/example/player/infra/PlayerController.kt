package com.example.player.infra

import com.example.player.domain.PlayerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/players")
class PlayerController(val playerService: PlayerService) {

    @PostMapping
    fun post() = playerService.create("")

    @GetMapping
    fun get() = playerService.get()

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: String) = playerService.get(id)

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: String) = playerService.delete(id)

}