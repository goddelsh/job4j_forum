package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Athority;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

    final private List<User> userList = new ArrayList<>();
    final private AtomicInteger aInteger = new AtomicInteger();

    public UserService() {
        User user = new User();
        user.setId(aInteger.getAndIncrement());
        user.setName("user");
        user.setPassword("$2a$10$a1Dgv1ooPKlMY5Eml/qA/.Danb4hufYjQp0e0yDmf4vaITha5nSp.");
            user.setEmail("user");
        user.setAthority(new Athority(2, "USER"));
        user.setPost(Set.of(Post.of("Продаю машину ладу 01.")));
        userList.add(user);
    }

    public List<User> getAll() {
        return this.userList;
    }

    public User getUser(String email, String password) {
        return userList.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst().orElse(null);
    }

    public User adduser(User user){
        user.setId(this.aInteger.getAndIncrement());
        userList.add(user);
        return user;
    }

}
