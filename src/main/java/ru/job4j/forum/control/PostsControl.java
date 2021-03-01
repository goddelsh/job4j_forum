package ru.job4j.forum.control;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.PostForm;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostsControl {

    private final PostService postService;
    private final UserService userService;

    public PostsControl(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }


    @GetMapping("/edit")
    public String edit(@RequestParam(value = "id", required = false) Integer id,
                       @RequestParam(value ="topic", required = false) Integer topic, Model model) {
        if(id != null) {
            model.addAttribute("post", postService.getById(id));
        }
        if(topic != null) {
            model.addAttribute("topic", postService.getById(topic));
        }
        return "edit";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute Post post, Authentication authentication) {
        post.setUser(userService.getUser(authentication.getName()));
        this.postService.edit(post);
        return "redirect:/";
    }

    @RequestMapping("/create")
    public String createPost(@ModelAttribute("PostForm") PostForm post,
                             Authentication authentication) {
        Post newPost = new Post();
        newPost.setName(post.getName());
        newPost.setDesc(post.getDesc());
        newPost.setUser(userService.getUser(authentication.getName()));
        if (post != null) {
            newPost.setTopic(postService.getById(post.getTopic()));
        }
        this.postService.addPost(newPost);
        return "redirect:/";
    }

}
