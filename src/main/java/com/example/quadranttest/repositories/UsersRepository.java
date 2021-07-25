package com.example.quadranttest.repositories;

import com.example.quadranttest.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);
    Users findByEmail(String email);
}
