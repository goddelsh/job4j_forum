package ru.job4j.forum.components;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.job4j.forum.service.UserService;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    final private UserService userService;

    final private PasswordEncoder encoder;

    public CustomAuthenticationProvider(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                var user = userService.getUser(authentication.getName());
                if (user != null && encoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
                    return new UsernamePasswordAuthenticationToken(
                            authentication.getName(),
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_USER")));
                } else {
                    return null;
                }
    }




    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

//  public void setEncoder(PasswordEncoder passwordEncoder) {
//        this.encoder = passwordEncoder;
//    }
}