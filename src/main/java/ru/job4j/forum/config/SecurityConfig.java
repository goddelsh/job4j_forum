package ru.job4j.forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.job4j.forum.components.CustomAuthenticationProvider;
import ru.job4j.forum.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final private UserService userService;
    final private CustomAuthenticationProvider customAuthenticationProvider;

    public SecurityConfig(UserService userService, CustomAuthenticationProvider customAuthenticationProvider) {
        this.userService = userService;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.customAuthenticationProvider);
//        var inMemory = auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).getUserDetailsService();
//       // inMemory.createUser(new User("manager", "$2a$10$a1Dgv1ooPKlMY5Eml/qA/.Danb4hufYjQp0e0yDmf4vaITha5nSp.", List.of(new SimpleGrantedAuthority("ROLE_USER"))));
//        this.userService.getAll()
//                    .forEach(user -> {
//                        inMemory.createUser(new User(user.getName(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER"))));
//                    });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/reg")
                .permitAll()
                .antMatchers("/**").hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}
