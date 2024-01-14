package com.example.fitnessbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.LongSummaryStatistics;

/**
 * @TimeStamp 2024-01-10 17:58
 * @ProjectDetails fitness-backend
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mID;
    private String planName;
    private String planDetails;
    private String calorieCount;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "mealPlan")
    private List<User> user;


}
