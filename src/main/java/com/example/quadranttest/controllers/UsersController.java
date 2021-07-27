package com.example.quadranttest.controllers;

import com.example.quadranttest.models.GeneralResponse;
import com.example.quadranttest.models.Users;
import com.example.quadranttest.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private GeneralResponse generalResponse;

    @PostMapping("/add-user")
    public GeneralResponse addUser(@RequestBody Users users) {
        if (!validateAddUser(users)) {
            return generalResponse;
        }
        HashMap<String, Object> mapUser = new HashMap<>();
        try {
            Users resUser = usersService.saveUsers(users);
            mapUser.put("user", resUser);
            generalResponse.setResultMsg("Success");
            generalResponse.setResultCd(200);
            generalResponse.setData(mapUser);

            return generalResponse;
        } catch (Exception e) {
            e.getMessage();
            generalResponse.setResultMsg(e.getMessage());
            generalResponse.setResultCd(403);
        }
        return generalResponse;
    }

    @PostMapping("/add-users")
    public List<Users> addUsers(@RequestBody List<Users> users) {
        return usersService.saveUsers(users);
    }

    @GetMapping("/users")
    public List<Users> findAllUsers() {
        return usersService.getUsers();
    }

    @GetMapping("/user/{id}")
    public Users findUserById(@PathVariable int id) {
        return usersService.getUsersById(id);
    }

    @PostMapping("/user/{username}")
    public Users findUserByUsername(@PathVariable String username) {
        return usersService.getUsersByUsername(username);
    }

    @PostMapping("/user/{email}")
    public Users findUserByEmail(@PathVariable String email) {
        return usersService.getUsersByEmail(email);
    }

    @PutMapping("/user")
    public Users updateUser(@RequestBody Users users) {
        return usersService.updateUsers(users);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id) {
        return usersService.deleteUsers(id);
    }

    private boolean validateAddUser(Users users) {
        boolean valid = true;
        generalResponse.setData(null);

        if (users.getUsername() == null || users.getUsername().equals("")) {
            generalResponse.setResultCd(401);
            generalResponse.setResultMsg("username field is mandatory");
            valid = false;
        }

        if (users.getEmail() == null || users.getEmail().equals("")) {
            generalResponse.setResultCd(401);
            generalResponse.setResultMsg("email field is mandatory");
            valid = false;
        }

        if (users.getEmail() != null) {
            if (!isValidEmailAddress(users.getEmail())) {
                generalResponse.setResultCd(401);
                generalResponse.setResultMsg("email format not valid");
                valid = false;
            }
        }

        if (users.getFullName() == null || users.getFullName().equals("")) {
            generalResponse.setResultCd(401);
            generalResponse.setResultMsg("full name field is mandatory");
            valid = false;
        }

        if (users.getPassword() == null || users.getPassword().equals("")) {
            generalResponse.setResultCd(401);
            generalResponse.setResultMsg("password field is mandatory");
            valid = false;
        }

        if (users.getPassword() != null) {
            if (users.getPassword().length() < 5) {
                generalResponse.setResultCd(401);
                generalResponse.setResultMsg("password length minimal 6 char");
                valid = false;
            }
        }

        return valid;
    }

    public static boolean isValidEmailAddress(String email) {
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
