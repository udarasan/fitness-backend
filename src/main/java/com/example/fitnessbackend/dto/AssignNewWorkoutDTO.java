package com.example.fitnessbackend.dto;

import com.example.fitnessbackend.dto.UserDTO;
import com.example.fitnessbackend.dto.WorkOutPlanDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignNewWorkoutDTO {
    private WorkOutPlanDTO workOutPlanDTO;
    private UserDTO userDTO;

}
