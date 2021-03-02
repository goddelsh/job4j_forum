package ru.job4j.forum.control;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.TestObj;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

@Controller
public class IndexControl {
    private final PostService posts;
    final private UserService userService;

    public IndexControl(PostService posts, UserService userService) {
        this.posts = posts;
        this.userService = userService;
    }



    @GetMapping({"/test"})
    public String test() {

        return "test";
    }

    @PostMapping({"/test"})
    public String testPost(@ModelAttribute TestObj test, Authentication authentication) {
        System.out.println(test);
        return "redirect:/";
    }


    @GetMapping({"/", "/index"})
    public String index(@RequestParam(value = "id", required = false) Integer id, Model model, Authentication authentication) {
        model.addAttribute("user", userService.getUser(authentication.getName()));
        model.addAttribute("topic", posts.getById(id));
        if(id != null) {
            model.addAttribute("posts", posts.getAllForId(id));
        } else {
            model.addAttribute("posts", posts.getAll());
        }
        return "index";
    }
}