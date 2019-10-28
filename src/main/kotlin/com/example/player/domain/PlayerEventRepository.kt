package com.example.player.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerEventRepository : CrudRepository<PlayerEvent, String>