package ru.job4j.forum.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AthoritiesRepository;
import ru.job4j.forum.repository.UsersRepository;

@Controller
public class RegControl {

    final private UsersRepository usersRepository;
    final private AthoritiesRepository athoritiesRepository;
    private final PasswordEncoder encoder;

    public RegControl(UsersRepository usersRepository, AthoritiesRepository athoritiesRepository, PasswordEncoder encoder) {
        this.usersRepository = usersRepository;
        this.athoritiesRepository = athoritiesRepository;
        this.encoder = encoder;
    }


    @GetMapping("/reg")
    public String reg(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "reg";
    }


    @PostMapping("/reg")
    public String reg(@ModelAttribute User user) {
        user.setAthority(athoritiesRepository.findById(1).orElse(null));
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            usersRepository.save(user);
        }catch (Exception ex) {
            return "redirect:/reg?error=Parametrs error";
        }

        return "redirect:/login";
    }
}
