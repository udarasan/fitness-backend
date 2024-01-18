package com.example.fitnessbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignNewMealPlanDTO {
    private MealPlanDTO mealPlanDTO;
    private UserDTO userDTO;
}
