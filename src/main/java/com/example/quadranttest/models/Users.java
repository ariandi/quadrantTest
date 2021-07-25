package com.example.quadranttest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String full_name;
    private String email;
    private String password;
    private String created_at;
    private String created_by;

    @Nullable
    private String updated_at;
    @Nullable
    private String updated_by;

}
