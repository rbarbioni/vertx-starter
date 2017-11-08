package br.com.rbarbioni.vertx;

import br.com.rbarbioni.vertx.verticle.CamelVerticle;
import br.com.rbarbioni.vertx.verticle.EventBusVerticle;
import br.com.rbarbioni.vertx.verticle.JobVerticle;
import br.com.rbarbioni.vertx.verticle.RouteVerticle;
import io.vertx.core.Vertx;

public class Application {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new RouteVerticle());
    vertx.deployVerticle(new EventBusVerticle());
    vertx.deployVerticle(new JobVerticle());
    vertx.deployVerticle(new CamelVerticle());
  }
}
