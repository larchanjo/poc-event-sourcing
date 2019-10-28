package com.example.player.infra

import com.example.player.domain.PlayerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/players")
class PlayerController(val service: PlayerService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@RequestBody body: CreatePlayerRequest) = service.create(body.name)

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun put(@PathVariable id: String, @RequestBody body: UpdatePlayerRequest) = service.update(id, body.score)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun get() = service.get()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getOne(@PathVariable id: String) = service.get(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOne(@PathVariable id: String) = service.delete(id)

}