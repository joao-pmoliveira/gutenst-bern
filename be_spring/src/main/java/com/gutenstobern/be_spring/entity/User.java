package com.gutenstobern.be_spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 255, unique = true, nullable = false)
    String username;

    @Column(length = 255, nullable = false)
    String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    UserRole role;

    public User() {
    }

    public User(String name, String password, UserRole userRole) {
        this.username = name;
        this.password = password;
        this.role = userRole;
    }

}
