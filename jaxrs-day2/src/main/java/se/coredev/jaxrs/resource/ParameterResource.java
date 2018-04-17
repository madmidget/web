package se.coredev.jaxrs.resource;

import se.coredev.jaxrs.resource.param.TestParam;

import javax.ws.rs.*;

@Path("parameters")
public final class ParameterResource {

    @GET
    public String testParameters(@BeanParam TestParam param) {
        return String.format("Value:%s, Size:%s, Sort:%s", param.getValue(), param.getSize(), param.getSort());
    }
}
