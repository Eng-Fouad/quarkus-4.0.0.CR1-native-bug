package org.acme;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Hello implements QuarkusApplication {

    @Override
    public int run(String... args) {
        System.out.println("Hello from Quarkus");
        return 0;
    }
}
