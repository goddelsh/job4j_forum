package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();

    public PostService() {
        posts.add(Post.of("Продаю машину ладу 01."));
    }

    public List<Post> getAll() {
        return posts;
    }

    public Post addPost(Post post) {
        posts.add(post);
        return post;
    }

    public Post edit(Post post) {
        for (Post targetPost : posts) {
            if (targetPost.getId() == post.getId()) {
                targetPost = post;
                break;
            }
        }
        return post;
    }
}