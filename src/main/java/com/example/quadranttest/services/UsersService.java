package com.example.quadranttest.services;

import com.example.quadranttest.models.Users;
import com.example.quadranttest.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users saveUsers(Users users) {
        return usersRepository.save(users);
    }

    public List<Users> saveUsers(List<Users> users) {
        return usersRepository.saveAll(users);
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public Users getUsersById(int id) {
        return usersRepository.findById(id).orElse(null);
    }

    public Users getUsersByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public Users getUsersByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public String deleteUsers(int id) {
        usersRepository.deleteById(id);
        return "user " + id + " deleted";
    }

    public Users updateUsers(Users users) {
        Users existingUser = usersRepository.findById(users.getId()).orElse(null);
        assert existingUser != null;
        existingUser.setUsername(users.getUsername());
        existingUser.setEmail(users.getEmail());
        existingUser.setFull_name(users.getFull_name());
        existingUser.setPassword(users.getPassword());
        existingUser.setUpdated_at(users.getUpdated_at());
        existingUser.setUpdated_by(users.getUpdated_by());
        return usersRepository.save(existingUser);
    }

}
