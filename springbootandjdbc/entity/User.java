package com.example.springbootandjdbc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="user_id")
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;

    public User() {

    }
}
