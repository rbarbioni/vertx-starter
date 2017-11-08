package br.com.rbarbioni.vertx.verticle;

import io.vertx.core.AbstractVerticle;

public class EventBusVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    super.start();

    vertx.eventBus().consumer("event-bus", message -> {
      System.out.println("EventBus -> " + message.body());
    });
  }
}
