package com.example.customer.domain

import java.util.*

data class Customer(val id: String = UUID.randomUUID().toString())
