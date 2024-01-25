package com.example.fitnessbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FitnessGoalDTO {
    private int goalId;
    private String goalName;
    private String goalDetails;
    private Date startDate;
    private Date endDate;
    private String status;
    private int userId;
}
