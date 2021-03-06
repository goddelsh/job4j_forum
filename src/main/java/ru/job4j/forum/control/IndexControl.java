package ru.job4j.forum.control;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.AthoritiesRepository;
import ru.job4j.forum.repository.PostsRepository;
import ru.job4j.forum.repository.UsersRepository;

@Controller
public class IndexControl {

    final private PostsRepository postsRepository;
    final private UsersRepository usersRepository;
    final private AthoritiesRepository athoritiesRepository;

    public IndexControl(PostsRepository postsRepository, UsersRepository usersRepository, AthoritiesRepository athoritiesRepository) {
        this.postsRepository = postsRepository;
        this.usersRepository = usersRepository;

        this.athoritiesRepository = athoritiesRepository;
    }


    @GetMapping({"/", "/index"})
    public String index(@RequestParam(value = "id", required = false) Integer id, Model model, Authentication authentication) {
        model.addAttribute("user", usersRepository.getByName(authentication.getName()));

        if(id != null) {
            var topic = postsRepository.findById(id).orElse(null);
            if (topic != null) {
                model.addAttribute("topic", topic);
            }
            model.addAttribute("posts", postsRepository.getForTopic(new Post(id)));
        } else {
            model.addAttribute("posts", postsRepository.getRootPosts());
        }
        return "index";
    }
}