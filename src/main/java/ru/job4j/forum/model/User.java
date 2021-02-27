package ru.job4j.forum.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User {
    private Integer id;
    private String password;
    private String name;
    private String email;
    private Set<Post> post = new HashSet<>();
    private Athority athority = new Athority(0, "NO_ROLE");
    private boolean enabled = true;

    public Set<Post> getPost() {
        return post;
    }

    public void setPost(Set<Post> post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, name, email);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Athority getAthority() {
        return athority;
    }

    public void setAthority(Athority athority) {
        this.athority = athority;
    }
}
