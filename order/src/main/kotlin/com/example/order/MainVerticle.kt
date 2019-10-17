package com.example.order

import com.example.customer.infra.handle.*
import com.example.customer.infra.worker.*
import io.vertx.core.AbstractVerticle
import io.vertx.core.DeploymentOptions
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router

class MainVerticle : AbstractVerticle() {

  override fun start(startPromise: Promise<Void>) {
    val httpServer = vertx.createHttpServer()
    val router = Router.router(vertx)

    // API's
    router.route().method(HttpMethod.POST).handler(CreateOrderHandler(vertx))
    router.route().method(HttpMethod.GET).handler(GetOrderHandler(vertx))
    router.route().method(HttpMethod.DELETE).handler(DeleteOrderHandler(vertx))

    // Worker's
    vertx.deployVerticle(CreateOrderWorker::class.java.name, DeploymentOptions().setWorker(true))
    vertx.deployVerticle(GetOrderWorker::class.java.name, DeploymentOptions().setWorker(true))
    vertx.deployVerticle(DeleteOrderWorker::class.java.name, DeploymentOptions().setWorker(true))

    httpServer.requestHandler(router).listen(8081) { http ->
      if (http.succeeded()) {
        startPromise.complete()
        println("HTTP server started on port ${http.result().actualPort()}")
      } else {
        startPromise.fail(http.cause());
      }
    }
  }

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      Vertx.vertx().deployVerticle(MainVerticle::class.java.name)
    }
  }

}
