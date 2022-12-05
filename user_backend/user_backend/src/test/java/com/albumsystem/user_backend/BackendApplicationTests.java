package com.albumsystem.user_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("njnu10001"));
        System.out.println(passwordEncoder.encode("njnu10002"));
        System.out.println(passwordEncoder.encode("njnu10003"));
        System.out.println(passwordEncoder.encode("njnu10004"));
        System.out.println(passwordEncoder.encode("njnu10005"));
        System.out.println(passwordEncoder.encode("njnu10006"));
    }

}
