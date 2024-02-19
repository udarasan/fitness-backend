package com.example.fitnessbackend.dto;

import com.example.fitnessbackend.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @TimeStamp 2024-01-09 07:34
 * @ProjectDetails fitness-backend
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainerDTO {
    private int tID;
    private String name;
    private String email;
    // Add other trainer-related fields as needed
    //private String age;
    private String password;
    private String category;

    private List<UserDTO> users;
}
