package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Athority;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

    final private Map<String, User> userList = new ConcurrentHashMap<>();
    final private AtomicInteger aInteger = new AtomicInteger();

    public UserService() {
        User user = new User();
        user.setId(aInteger.getAndIncrement());
        user.setName("user");
        user.setPassword("$2a$10$a1Dgv1ooPKlMY5Eml/qA/.Danb4hufYjQp0e0yDmf4vaITha5nSp.");
            user.setEmail("user");
        user.setAthority(new Athority(2, "USER"));
        //user.setPost(Set.of(Post.of("Продаю машину ладу 01.")));
        userList.putIfAbsent(user.getName(), user);
    }

    public User adduser(User user){
        var u = userList.get(user.getName());
        if (u != null) {
            throw new IllegalStateException("User already exist!");
        }
        user.setId(this.aInteger.getAndIncrement());
        userList.putIfAbsent(user.getName(), user);
        return user;
    }

    public User getUser(String name) {
        return userList.get(name);
    }
}
