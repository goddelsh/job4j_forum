package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    final private AtomicInteger aInteger = new AtomicInteger();

    public List<Post> getAll() {
        return posts.values()
                .stream()
                .filter(p -> p.getTopic() == null).
                        collect(Collectors.toList());
    }

    public Post getById(Integer id) {
        return id != null ? posts.get(id) : null;
    }


    public Post addPost(Post post) {
        post.setId(aInteger.getAndIncrement());
        posts.putIfAbsent(post.getId(), post);
        return post;
    }

    public Post edit(Post post) {
        posts.computeIfPresent(post.getId(), (key, value) -> value);
        return post;
    }

    public List<Post> getAllForId(Integer id) {
        return posts.values().stream()
                .filter( p -> p.getTopic() != null
                        && p.getTopic().getId().equals(id)).
                        collect(Collectors.toList());
    }
}