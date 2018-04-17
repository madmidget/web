package se.coredev.jaxrs.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import se.coredev.jaxrs.resource.MessageResource;
import se.coredev.jaxrs.resource.ParameterResource;
import se.coredev.jaxrs.resource.UserResource;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(MessageResource.class);
        register(UserResource.class);
        register(ParameterResource.class);
    }
}
