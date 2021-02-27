package ru.job4j.forum.model;

import java.util.Objects;

public class Athority {
    private Integer id;
    private String role;

    public Athority(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    public Athority() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athority athority = (Athority) o;
        return Objects.equals(id, athority.id) &&
                Objects.equals(role, athority.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
