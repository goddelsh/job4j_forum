package ru.job4j.forum.control;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.PostForm;
import ru.job4j.forum.repository.PostsRepository;
import ru.job4j.forum.repository.UsersRepository;

@Controller
public class PostsControl {

    final private UsersRepository usersRepository;
    final private PostsRepository postsRepository;

    public PostsControl(UsersRepository usersRepository, PostsRepository postsRepository) {
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
    }


    @GetMapping("/edit")
    public String edit(@RequestParam(value = "id", required = false) Integer id,
                       @RequestParam(value ="topic", required = false) Integer topic, Model model) {
        if(id != null) {
            model.addAttribute("post", postsRepository.findById(id).orElse(null));
        }
        if(topic != null) {
            model.addAttribute("topic", postsRepository.findById(topic).orElse(null));
        }
        return "edit";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute Post post, Authentication authentication) {
        post.setUser(usersRepository.getByName(authentication.getName()));
        this.postsRepository.save(post);
        return "redirect:/";
    }

    @RequestMapping("/create")
    public String createPost(@ModelAttribute("PostForm") PostForm post,
                             Authentication authentication) {
        Post newPost = new Post();
        newPost.setName(post.getName());
        newPost.setDescription(post.getDescription());
        newPost.setUser(usersRepository.getByName(authentication.getName()));
        if (post != null && post.getTopic() != null) {
            newPost.setTopic(postsRepository.findById(post.getTopic()).orElse(null));
        }
        this.postsRepository.save(newPost);
        return "redirect:/";
    }

}
