package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();
    final private AtomicInteger aInteger = new AtomicInteger();

    public List<Post> getAll() {
        return posts.stream()
                .filter(p -> p.getTopic() == null).
                        collect(Collectors.toList());
    }

    public Post getById(Integer id) {
        Post result = null;
        for(Post elem : this.posts) {
            if (elem.getId().equals(id)) {
                result = elem;
                break;
            }
        }
        return result;
    }


    public Post addPost(Post post) {
        post.setId(aInteger.getAndIncrement());
        posts.add(post);
        return post;
    }

    public Post edit(Post post) {
        Collections.replaceAll(this.posts, this.getById(post.getId()), post);
        return post;
    }

    public List<Post> getAllForId(Integer id) {
        return posts.stream()
                .filter( p -> p.getTopic() != null
                        && p.getTopic().getId().equals(id)).
                        collect(Collectors.toList());
    }
}