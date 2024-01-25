package com.example.fitnessbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"date", "meal", "user_id"})
})
public class MealRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mrID;

    private Date date;
    private String meal;

    @Column(columnDefinition = "TEXT")
    private String details;

    private int calories;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
