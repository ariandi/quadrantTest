package com.example.quadranttest.services;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.example.quadranttest.models.Users;
import com.example.quadranttest.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Users userData = getUserData(username);
            if (userData == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }

            return new User(userData.getUsername(), userData.getPassword(), new ArrayList<>());
        } catch (NoSuchAlgorithmException e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Users getUserData(String username) throws NoSuchAlgorithmException {
        Users userData = null;
        try {
            userData = usersRepository.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userData;
    }

}
