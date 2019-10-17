package com.example.customer.infra.worker

import io.vertx.core.AbstractVerticle
import io.vertx.core.Handler
import io.vertx.core.json.JsonObject

class CreateCustomerWorker : AbstractVerticle() {

  companion object {

    val EVENT_BUS = this::class.java.simpleName

  }

  override fun start() {
    vertx.eventBus().consumer<String>(EVENT_BUS, Handler {

      it.reply(JsonObject())
    })
  }

}
