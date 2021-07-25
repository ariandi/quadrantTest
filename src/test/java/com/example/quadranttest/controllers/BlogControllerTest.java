package com.example.quadranttest.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

class BlogControllerTest {
    @Test
    public void hashingPassword() throws NoSuchAlgorithmException {
        String password = BCrypt.hashpw("password", BCrypt.gensalt(12));

        System.out.println(password);
    }
}