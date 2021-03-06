package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.List;

@Repository
public interface PostsRepository extends CrudRepository<Post, Integer> {

    @Query("select p from #{#entityName} p where p.topic = ?1")
    List<Post> getForTopic(Post topic);

    @Query("select p from #{#entityName} p where p.topic is null")
    List<Post> getRootPosts();
}
