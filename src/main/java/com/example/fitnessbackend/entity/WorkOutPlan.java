package com.example.fitnessbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "workOutPlan")
    private List<User> user;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "workOutPlan")
    private List<Progress> progresses;
}
