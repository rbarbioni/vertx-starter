package br.com.rbarbioni.vertx.repository;

import io.vertx.core.VertxException;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

public class ProductRepository extends Repository {

    public ProductRepository() {

        getJdbcClient().getConnection(c -> {
            if (!c.succeeded()) {
                throw new VertxException("Connection Error", c.cause());
            }
            
            final SQLConnection conn = c.result();

            conn.query("select * from product", select -> {

                if (!select.succeeded()) {
                    conn.close();
                    throw new VertxException("Connection Error", select.cause());
                }

            });

        });
    }
}
