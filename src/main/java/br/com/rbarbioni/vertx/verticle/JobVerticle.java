package br.com.rbarbioni.vertx.verticle;

import io.vertx.core.AbstractVerticle;

public class JobVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    super.start();

    vertx.setPeriodic(1000L, timne -> {
      vertx.eventBus().publish("event-bus", "My Event-Bus Message");
    });
  }
}
