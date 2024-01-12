package com.example.fitnessbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TimeStamp 2024-01-10 17:58
 * @ProjectDetails fitness-backend
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkOutPlanDTO {

    private int mid;
    private String planName;
    private String planDetails;
    private String burnsCalorieCount;


}
