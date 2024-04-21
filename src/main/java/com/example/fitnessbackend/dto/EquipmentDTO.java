package com.example.fitnessbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO {
    private int eid;
    private String equipmentName;
    private String equipmentDetail;
    private Date purchaseDate;
    private String checkCondition;
    private Date warrantyEndDate;
}
