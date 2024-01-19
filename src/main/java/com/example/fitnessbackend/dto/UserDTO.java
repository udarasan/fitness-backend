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
    private String uID;
    private String name;
    private String email;
    private String password;
    private  int age;
    private String gender;
    private int trainer_id;
    private int meal_plan_id;
    private int workout_id;
}
