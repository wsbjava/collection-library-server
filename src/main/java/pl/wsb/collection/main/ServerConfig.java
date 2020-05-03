package pl.wsb.collection.main;

import org.glassfish.jersey.server.ResourceConfig;
import pl.wsb.collection.security.annotation.AuthenticateFilter;

public class ServerConfig extends ResourceConfig {

    public ServerConfig() {
        register(AuthenticateFilter.class);
    }
}
