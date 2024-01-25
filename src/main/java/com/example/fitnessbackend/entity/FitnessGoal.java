package com.example.fitnessbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FitnessGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goalId;

    private String goalName;

    @Column(columnDefinition = "TEXT")
    private String goalDetails;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
