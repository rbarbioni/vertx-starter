package br.com.rbarbioni.vertx;

import br.com.rbarbioni.vertx.verticle.CamelVerticle;
import br.com.rbarbioni.vertx.verticle.EventBusVerticle;
import br.com.rbarbioni.vertx.verticle.JobVerticle;
import br.com.rbarbioni.vertx.verticle.RouteVerticle;
import io.vertx.core.AbstractVerticle;

public class AppVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    super.start();
    vertx.deployVerticle(new RouteVerticle());
    vertx.deployVerticle(new EventBusVerticle());
    vertx.deployVerticle(new JobVerticle());
    vertx.deployVerticle(new CamelVerticle());
  }
}
