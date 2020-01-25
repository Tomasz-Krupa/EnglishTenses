package org.example;

import ratpack.exec.Blocking;
import ratpack.guice.Guice;
import ratpack.hikari.HikariModule;
import ratpack.jackson.Jackson;
import ratpack.server.RatpackServer;

import javax.sql.DataSource;
import java.sql.Connection;

public class Zad6 {
    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server
                .registry(Guice.registry(bindings ->
                        bindings.module(HikariModule.class, config -> {
                            config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
                            config.setUsername("postgres");
                            config.setPassword("Natalia2012");
                            config.addDataSourceProperty("URL", "jdbc:postgresql://localhost:5432/postgres");
                        })
                ))

                .handlers(
                        chain -> chain
                                .get("orders", ctx -> Blocking
                                        .get(() -> {
                                            try (Connection connection = ctx.get(DataSource.class).getConnection()) {
                                               OrderRepository orderRepository= new OrderRepository();
                                               orderRepository.setConnection(connection);

                                               return orderRepository.loadLastOrders(10);
                                            }
                                        })
                                        .then(e -> ctx.render(Jackson.json(e)))

                                )
                )
        );
    }
}


