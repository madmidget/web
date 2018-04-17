package se.coredev.jaxrs.resource.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public final class TestParam {

    @QueryParam("value")
    @DefaultValue("no value")
    private String value;

    @QueryParam("size")
    @DefaultValue("0")
    private int size;

    @QueryParam("sort")
    @DefaultValue("asc")
    private String sort;

    public String getValue() {
        return value;
    }

    public int getSize() {
        return size;
    }

    public String getSort() {
        return sort;
    }
}
