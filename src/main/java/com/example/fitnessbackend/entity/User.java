package com.example.fitnessbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private  int age;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private WorkOutPlan workOutPlan;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private List<Progress> progresses;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private List<Chat> chat;
//    @ManyToOne
//    @JoinColumn(name = "equipment_id")
//    private Equipment equipment;


}
