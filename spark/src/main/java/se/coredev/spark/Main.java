package se.coredev.spark;

import static spark.Spark.get;
import static spark.Spark.post;

public final class Main {

    public static void main(String[] args) {
        get("/hello", (request, response) -> "Hello Spark!");
    }
}
