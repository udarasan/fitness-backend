package com.example.fitnessbackend.dto;

import com.example.fitnessbackend.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgressDTO {

    private int pid;


    private double height;


    private double weight;

    private int userId;


    private Date date;
}
