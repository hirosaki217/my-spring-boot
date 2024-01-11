package org.myapp;


import org.myapp.config.RsaKeyConfigProperties;
import org.myapp.model.Role;
import org.myapp.model.User;
import org.myapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@EnableConfigurationProperties(RsaKeyConfigProperties.class)
@SpringBootApplication
public class Oauth2JwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2JwtApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeUser(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {

            User user = new User();
            user.setUsername("exampleuser");
            user.setPassword(passwordEncoder.encode("examplepassword"));
            Role role = new Role();
            role.setUser(user);
            role.setRole("USER");
            Role role2 = new Role();
            role2.setUser(user);
            role2.setRole("ADMIN");
            Set<Role> roles =new HashSet<>();
            roles.add(role2);
            roles.add(role);
            user.setRoles(roles);
            // Save the user to the database
            userRepository.save(user);
        };
    }
}