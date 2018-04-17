package se.coredev.jaxrs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class Role {

    @XmlElement
    private final String name;

    private Role() {
        name = null;
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
