package com.example.fitnessbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TimeStamp 2024-01-10 17:58
 * @ProjectDetails fitness-backend
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkOutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wID;
    private String planName;
    private String planDetails;
    private String burnsCalorieCount;


}
