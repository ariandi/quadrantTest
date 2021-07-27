package com.example.quadranttest.services;

import com.example.quadranttest.models.Users;
import com.example.quadranttest.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users saveUsers(Users users) throws NoSuchAlgorithmException {
        if (users.getCreatedAt() == null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            users.setCreatedAt(dtf.format(now));
        }

        if (users.getCreatedBy() == null) {
            users.setCreatedBy(users.getUsername());
        }

        if (users.getPassword() != null) {
            String password = hashingPassword(users.getPassword());
            users.setPassword(password);
        }

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
        existingUser.setFullName(users.getFullName());
        existingUser.setPassword(users.getPassword());
        existingUser.setUpdatedAt(users.getUpdatedAt());
        existingUser.setUpdatedBy(users.getUpdatedBy());
        return usersRepository.save(existingUser);
    }

    public String hashingPassword(String pwd) throws NoSuchAlgorithmException {
        return BCrypt.hashpw(pwd, BCrypt.gensalt(12));
    }

}
