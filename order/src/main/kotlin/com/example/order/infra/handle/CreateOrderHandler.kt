package com.example.customer.infra.handle

import com.example.customer.infra.worker.CreateOrderWorker
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.ext.web.RoutingContext

class CreateOrderHandler(val vertx: Vertx) : Handler<RoutingContext> {

  override fun handle(routingContext: RoutingContext) {
    vertx.eventBus().request<String>(CreateOrderWorker.EVENT_BUS, routingContext.bodyAsString, {
      // TODO
      routingContext.response().end(it.result().body())
    })
  }

}
