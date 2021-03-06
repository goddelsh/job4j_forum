package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {
    User getByName(String name);
}
