package ru.job4j.forum.model;

import java.util.Calendar;
import java.util.Objects;

public class Post {
    private Integer id;
    private String name;
    private String desc;
    private Calendar created;
    private User user;
    private Post topic;

    public Post getTopic() {
        return topic;
    }

    public void setTopic(Post topic) {
        this.topic = topic;
    }


    public static Post of(Integer id) {
        Post post = new Post();
        post.id = id;
        return post;
    }

    public static Post of(String name) {
        Post post = new Post();
        post.name = name;
        return post;
    }
    public static Post of(Integer id, String name) {
        Post post = new Post();
        post.name = name;
        post.id = id;
        return post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}