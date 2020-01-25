package org.example;

import ratpack.jackson.Jackson;
import ratpack.server.RatpackServer;

public class App {
    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server
                .handlers(
                        chain -> chain
                                .get(ctx -> ctx.render("Hello World!"))     //dla met. get renderuje napis
                                .get("hello", ctx -> ctx.render("Hello Ratpack"))           //ctx=context
                                .get("hello2", ctx -> {
                                    Car car = new Car();
                                    ctx.render(Jackson.json(car));
                                    ctx.render(Jackson.json(car));
                                })
                )
        );
    }
}

class  Car{
    long id=1;
    String name= "Fiat";

    public Car(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Car(long id) {
        this.id = id;
            }

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
