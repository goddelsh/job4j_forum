package ru.job4j.forum.components;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.job4j.forum.service.UserService;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    final private UserService userService;

    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var user = this.userService.getUser(authentication.getName());

        if (user != null) {
            return new UsernamePasswordAuthenticationToken(
                    user.getName(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_USER")));
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
