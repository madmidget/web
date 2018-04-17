package se.coredev.jaxrs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public final class User {

    @XmlElement
    private final String id;
    @XmlElement
    private final String username;
    @XmlElement
    private final String password;
    @XmlElement(name = "role")
    @XmlElementWrapper(name = "roles")
    private final List<Role> roles;

    private User() {
        id = null;
        username = null;
        password = null;
        roles = null;
    }

    public User(String id, String username, String password, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
