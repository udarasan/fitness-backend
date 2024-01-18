package com.example.fitnessbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;
    private String equipmentName;
    @Column(columnDefinition = "TEXT")
    private String equipmentDetail;
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;


}
