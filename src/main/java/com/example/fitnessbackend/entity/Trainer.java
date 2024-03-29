package com.example.fitnessbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @TimeStamp 2024-01-09 07:27
 * @ProjectDetails fitness-backend
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tID;
    private String name;
    private String email;
    // Add other trainer-related fields as needed
    //private String age;
    private String password;
    private String category;
    @Column(columnDefinition = "varchar(255) default 'active'")
    private String status;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "trainer")
    private List<User> users;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "trainer")
    private List<Chat> chat;

}
