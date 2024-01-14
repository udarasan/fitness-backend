package com.example.fitnessbackend.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TimeStamp 2024-01-08 23:23
 * @ProjectDetails fitness-backend
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private int uID;
    private String name;
    private String email;
    private String password;
    private int trainer_id;
    private String meal_id;
    private String workout_id;
}
