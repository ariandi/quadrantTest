package com.example.quadranttest.controllers;

import com.example.quadranttest.models.Users;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static org.junit.jupiter.api.Assertions.*;

class UsersControllerTest {

    Users users = new Users();

    @Test
    void addUser() {
        users.setEmail("db_duabelas@yahoo.com");
        users.setUsername("ariandin1411");
        users.setFullName("ariandi Nugraha");
        users.setPassword("123456");

        assertTrue(validateAddUser(users));

        users.setEmail(null);
        assertFalse(validateAddUser(users));

        users.setEmail("tests");
        assertFalse(validateAddUser(users));

        users.setUsername(null);
        assertFalse(validateAddUser(users));

        users.setFullName(null);
        assertFalse(validateAddUser(users));

        users.setPassword(null);
        assertFalse(validateAddUser(users));
    }

    @Test
    void findUserById() {
    }

    @Test
    void findUserByUsername() {
    }

    void findUserByEmail() {

    }

    boolean validateAddUser(Users users) {
        boolean valid = true;

        if (users.getUsername() == null || users.getUsername().equals("")) {
            valid = false;
        }

        if (users.getEmail() == null || users.getEmail().equals("")) {
            valid = false;
        }

        if (users.getEmail() != null) {
            if (!isValidEmailAddress(users.getEmail())) {
                valid = false;
            }
        }

        if (users.getFullName() == null || users.getFullName().equals("")) {
            valid = false;
        }

        if (users.getPassword() == null || users.getPassword().equals("")) {
            valid = false;
        }

        if (users.getPassword() != null) {
            if (users.getPassword().length() < 5) {
                valid = false;
            }
        }

        return valid;
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}