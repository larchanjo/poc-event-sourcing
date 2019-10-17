package com.example.customer.infra.handle

import com.example.customer.infra.worker.DeleteOrderWorker
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.ext.web.RoutingContext

class DeleteOrderHandler(val vertx: Vertx) : Handler<RoutingContext> {

  override fun handle(routingContext: RoutingContext) {
    vertx.eventBus().request<String>(DeleteOrderWorker.EVENT_BUS, routingContext.bodyAsString, {
      // TODO
      routingContext.response().end(it.result().body())
    })
  }

}
