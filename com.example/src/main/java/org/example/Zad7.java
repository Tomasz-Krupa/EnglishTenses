package org.example;

import ratpack.exec.Blocking;
import ratpack.guice.Guice;
import ratpack.hikari.HikariModule;
import ratpack.jackson.Jackson;
import ratpack.server.RatpackServer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Zad7 {
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

//                .handlers(
//                        chain -> chain
//                                .get(ctx -> ctx.render("Hello World!"))
//                                .path("orders/:number", ctx -> ctx.byMethod(
//                                        method->method
//                                    .get("orders/:id", ctx -> Blocking
//                                        .get(() -> {
//                                            try (Connection connection = ctx.get(DataSource.class).getConnection()) {
//                                                String id = ctx.getPathBinding().getAllTokens().get("id");
//                                                int idAsInt= Integer.parseInt(id);
//
//                                                OrderRepository orderRepository= new OrderRepository();
//                                               orderRepository.setConnection(connection);
//                                               return orderRepository.loadById(idAsInt);
//                                            }
//                                        })
//                                        .then(e -> ctx.render(Jackson.json(e)))                     //po gecie rób to
//                                )
//
//                                .delete(())-> Blocking
//                                    .get(() -> {
//                                    try (Connection connection = ctx.get(DataSource.class).getConnection()) {
//                                        String number = ctx.getPathBinding().getAllTokens().get("number");
//                                        int numberAsInt= Integer.parseInt(number);
//                                        OrderRepository orderRepository= new OrderRepository();
//                                        orderRepository.setConnection(connection);
//                                       // PreparedStatement statement = connection.prepareStatement("delete from orders where number=?");
//                                        PreparedStatement statement = connection.prepareStatement("delete from orders where number=?");
//                                        statement.setInt(1, numberAsInt);
//
//
//                                        return orderRepository.deleteOrder(numberAsInt);
//                                                //ctx.getResponse().beforeSend(response -> response.status(404));
//                                    }
//                                })
//                                .then(deleteResult -> ctx.render(Jackson.json(e)))
//                        )
        );
    }
}


