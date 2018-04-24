package se.coredev.spark;

import org.jooby.Jooby;

public final class Main extends Jooby {

    {
        get("/", () -> "Hey Jooby!");
    }

    public static void main(final String[] args) {
        run(Main::new, args);
    }
}
