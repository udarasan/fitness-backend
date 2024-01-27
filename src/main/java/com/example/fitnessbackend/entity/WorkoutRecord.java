package com.example.fitnessbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"date", "user_id"})
})
public class WorkoutRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wrID;

    private Date date;
    private String workout;

    @Column(columnDefinition = "TEXT")
    private String details;

    private int calories;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
