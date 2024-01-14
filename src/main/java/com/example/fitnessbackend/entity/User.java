package com.example.fitnessbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TimeStamp 2024-01-08 23:18
 * @ProjectDetails fitness-backend
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uID;
    private String name;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlanId;

    @ManyToOne
    @JoinColumn(name = "workout_plan_id")
    private WorkOutPlan workOutPlanId;


}
