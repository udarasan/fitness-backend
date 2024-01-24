package com.example.fitnessbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MealRecordDTO {
    private int mrID;
    private Date date;
    private String meal;
    private String details;
    private int calories;
    private int userId;
}
