package com.example.fitnessbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOutRecordDTO {
    private int wrID;
    private Date date;
    private String workout;
    private String details;
    private int calories;
    private int userId;
}
