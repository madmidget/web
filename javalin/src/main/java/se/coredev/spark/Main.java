package se.coredev.spark;

import io.javalin.Javalin;

public final class Main {

    public static void main(String[] args) {
        Javalin app = Javalin.start(7000);
        app.get("/", ctx -> ctx.result("Hello Javalin"));
    }
}
