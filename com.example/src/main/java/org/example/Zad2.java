package org.example;

import ratpack.jackson.Jackson;
import ratpack.server.RatpackServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zad2 {
    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server
                .handlers(
                        chain -> chain
                                .get(ctx -> ctx.render("Hello World!"))
                                .get("hello3", ctx -> ctx.render("hi"))
                                .get("cars", ctx -> {
                                        List<Car> cars2 = createCarListSimple();
                                        ctx.render(Jackson.json(cars2));
                                })
                                .get("cars2", ctx -> {
                                        ArrayList<Car> cars = createCarList();
                                        ctx.render(Jackson.json(cars));
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


