package br.com.rbarbioni.vertx.service;


import br.com.rbarbioni.vertx.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.VertxException;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


public class ProductService {

  private List<Product> products = Arrays.asList(
    new Product(1L, "Smartphone", BigDecimal.valueOf(499.99)),
    new Product(2L, "VideGame", BigDecimal.valueOf(399.99)),
    new Product(3L, "Notebook", BigDecimal.valueOf(999.99))
  );

  private ObjectMapper objectMapper = new ObjectMapper();

  public void findAll(RoutingContext context){
    HttpServerResponse response = context.response();
    response.putHeader("content-type", "application/json");
    response.end(Json.encodePrettily(products));
  }

  public void findById(RoutingContext context){

    HttpServerResponse response = context.response();
    response.putHeader("content-type", "application/json");
    Long id = Long.valueOf(context.request().getParam("id"));


    response.end(Json.encodePrettily(this.products.stream()
      .filter(product -> product.getId().equals(id))
      .findFirst()
      .orElseThrow(() -> new VertxException("404"))));
  }

  public void update(RoutingContext context){

    HttpServerResponse response = context.response();
    response.putHeader("content-type", "application/json");
    Long id = Long.valueOf(context.request().getParam("id"));

    try {
      final Product body = this.objectMapper.readValue(context.getBodyAsString(), Product.class);
      response.end(Json.encodePrettily(this.products.stream()
        .filter(product -> product.getId().equals(id))
        .findFirst()
        .map(product -> {
          product.setName(body.getName());
          product.setPrice(body.getPrice());
          return product;
        })
        .orElseThrow(() -> new VertxException("404"))));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void create(RoutingContext context){
    HttpServerResponse response = context.response();
    response.putHeader("content-type", "application/json");
    try {
      Product product = this.objectMapper.readValue(context.getBodyAsString(), Product.class);
      product.setId(Long.valueOf(products.size() + 1));
      products.add(product);
      response.end(Json.encodePrettily(products));
    } catch (IOException e) {
      throw new RuntimeException("Error", e);
    }
  }
}
