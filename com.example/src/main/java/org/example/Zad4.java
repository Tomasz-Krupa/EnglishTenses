package org.example;

import ratpack.guice.Guice;
import ratpack.hikari.HikariModule;
import ratpack.jackson.Jackson;
import ratpack.server.RatpackServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zad4 {
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
                                .get(ctx -> ctx.render("Hello World!"))
                                .get("hello", ctx -> ctx.render("hi"))
                                .get("cars", ctx -> {
                                    ArrayList<Car> cars = createCarList();
                                    ctx.getResponse().beforeSend(response -> response.status(400));         //ust. status
                                    ctx.render(Jackson.json(cars));
                                })
                                .get("cars/:id", ctx -> {
                                    String id = ctx.getPathBinding().getAllTokens().get("id");
                                    ctx.render(Jackson.json(new Car(Long.parseLong(id))));                  //pobiera zawsze Fiata

                                }).get("cars2/:id", ctx -> {
                                    ArrayList<Car> cars = createCarList();
                                    String id = ctx.getPathBinding().getAllTokens().get("id");
                                    ctx.render(Jackson.json(cars.get(Integer.parseInt(id)-1)));             //pobiera obiekt z listy cars
                                })
                )
        );
    }

    private static List<Car> createCarListSimple() {
        return new ArrayList<>(Arrays.asList(
                                            new Car(10, "A"),
                                            new Car (11, "B")));
    }

    private static ArrayList<Car> createCarList() {
        Car car1= new Car();
        Car car2= new Car(2, "Ford");
        Car car3= new Car(3, "Opel");
        ArrayList<Car> cars= new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        return cars;
    }



}


