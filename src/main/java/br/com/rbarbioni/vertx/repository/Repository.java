package br.com.rbarbioni.vertx.repository;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

public class Repository {

    public JDBCClient getJdbcClient (){

        return JDBCClient.createShared(Vertx.vertx(), new JsonObject()
                .put("url", "jdbc:hsqldb:mem:test?shutdown=true")
                .put("driver_class", "org.hsqldb.jdbcDriver")
                .put("max_pool_size", 30)
                .put("user", "SA")
                .put("password", ""));
    }
}
