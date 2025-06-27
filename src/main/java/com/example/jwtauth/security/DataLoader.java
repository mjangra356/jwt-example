package com.example.jwtauth.security;

import com.example.jwtauth.model.UserEntity;
import com.example.jwtauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired private PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        userRepository.save(new UserEntity("admin", encoder.encode("admin123"), "ROLE_ADMIN,ROLE_USER"));
        userRepository.save(new UserEntity("user", encoder.encode("user123"), "ROLE_USER"));
    }
}
