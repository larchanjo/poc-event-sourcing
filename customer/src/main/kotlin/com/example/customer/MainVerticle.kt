package com.example.customer

import com.example.customer.infra.handle.CreateCustomerHandler
import com.example.customer.infra.handle.DeleteCustomerHandler
import com.example.customer.infra.handle.GetCustomerHandler
import com.example.customer.infra.worker.CreateCustomerWorker
import com.example.customer.infra.worker.DeleteCustomerWorker
import com.example.customer.infra.worker.GetCustomerWorker
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
    router.route().method(HttpMethod.POST).handler(CreateCustomerHandler(vertx))
    router.route().method(HttpMethod.GET).handler(GetCustomerHandler(vertx))
    router.route().method(HttpMethod.DELETE).handler(DeleteCustomerHandler(vertx))

    // Worker's
    vertx.deployVerticle(CreateCustomerWorker::class.java.name, DeploymentOptions().setWorker(true))
    vertx.deployVerticle(GetCustomerWorker::class.java.name, DeploymentOptions().setWorker(true))
    vertx.deployVerticle(DeleteCustomerWorker::class.java.name, DeploymentOptions().setWorker(true))

    httpServer.requestHandler(router).listen(8080) { http ->
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
