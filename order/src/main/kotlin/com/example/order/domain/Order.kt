package com.example.order.domain

import java.util.*

data class Order(val id: String = UUID.randomUUID().toString())
