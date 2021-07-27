package com.example.quadranttest.controllers;

import com.example.quadranttest.models.Users;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.security.NoSuchAlgorithmException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BlogControllerTest {

    Users users = new Users();

    @Test
    public void hashingPassword() throws NoSuchAlgorithmException {
        String pwd = "password";
        String password = BCrypt.hashpw(pwd, BCrypt.gensalt(12));

        System.out.println(password);
    }
}