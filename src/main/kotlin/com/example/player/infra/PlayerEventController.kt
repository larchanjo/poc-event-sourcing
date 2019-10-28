package com.example.player.infra

import com.example.player.domain.PlayerEventService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/players")
class PlayerEventController(val service: PlayerEventService) {

    @GetMapping("/{id}/events")
    @ResponseStatus(HttpStatus.OK)
    fun getOne(@PathVariable id: String) = service.get(id)

}