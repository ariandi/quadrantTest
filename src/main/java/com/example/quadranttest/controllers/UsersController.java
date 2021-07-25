package com.example.quadranttest.controllers;

import com.example.quadranttest.models.Users;
import com.example.quadranttest.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/add-user")
    public Users addUser(@RequestBody Users users) {
        return usersService.saveUsers(users);
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
}
