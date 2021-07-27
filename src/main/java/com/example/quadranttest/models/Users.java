package com.example.quadranttest.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
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

    @Column(name = "full_name")
    @JsonSetter("full_name")
    private String fullName;
    private String email;
    private String password;

    @Column(name = "created_at")
    @JsonSetter("created_at")
    private String createdAt;

    @Column(name = "created_by")
    @JsonSetter("created_by")
    private String createdBy;

    @Nullable
    @Column(name = "updated_at")
    @JsonSetter("updated_at")
    private String updatedAt;

    @Nullable
    @Column(name = "updated_by")
    @JsonSetter("updated_by")
    private String updatedBy;

}
