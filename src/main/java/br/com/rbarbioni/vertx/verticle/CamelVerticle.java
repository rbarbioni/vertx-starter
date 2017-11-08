package br.com.rbarbioni.vertx.verticle;

import io.vertx.camel.CamelBridge;
import io.vertx.camel.CamelBridgeOptions;
import io.vertx.camel.InboundMapping;
import io.vertx.core.AbstractVerticle;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    super.start();

    CamelContext camel = new DefaultCamelContext();
    camel.addRoutes(new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        from("jetty://http://0.0.0.0:8889" + "/camel")
          .process(exchange -> {
            exchange.getIn().setBody("Camel message");
          })
          .to("direct:camel")
        .end();
      }
    });

    CamelBridge bridge = CamelBridge.create(vertx, new CamelBridgeOptions(camel)
      .addInboundMapping(InboundMapping.fromCamel("direct:camel").toVertx("event-bus")));

    camel.start();
    bridge.start();

  }
}
