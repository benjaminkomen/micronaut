package benjaminkomen.micronaut;

import io.micronaut.runtime.Micronaut;

public class Application {
    public static void main(String[] args) {
        String port = System.getenv("PORT");
        if(port != null) {
            System.setProperty("micronaut.server.port", port);
        }
        Micronaut.run(Application.class);
    }
}
