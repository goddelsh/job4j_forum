package ru.job4j.forum.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AthorityService;
import ru.job4j.forum.service.UserService;

@Controller
public class RegControl {

    final private UserService userService;
    final private AthorityService athorityService;
    private final PasswordEncoder encoder;

    public RegControl(UserService userService, AthorityService athorityService, PasswordEncoder encoder) {
        this.userService = userService;
        this.athorityService = athorityService;
        this.encoder = encoder;
    }


    @GetMapping("/reg")
    public String reg(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "reg";
    }


    @PostMapping("/reg")
    public String reg(@ModelAttribute User user) {
        user.setAthority(athorityService.getAthorityByRole("USER"));
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            userService.adduser(user);
        }catch (IllegalStateException ex) {
            return "redirect:/reg?error="+ex.getMessage();
        }

        return "redirect:/login";
    }
}
