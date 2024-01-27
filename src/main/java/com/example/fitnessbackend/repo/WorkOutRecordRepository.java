package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.MealRecord;
import com.example.fitnessbackend.entity.WorkoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface WorkOutRecordRepository extends JpaRepository<WorkoutRecord,Integer> {
    @Query(value = "SELECT * FROM workout_record WHERE user_id = ?1", nativeQuery = true)
    List<WorkoutRecord> findByUser(int id);

    @Query(value = "SELECT * FROM workout_record WHERE date = ?1 AND user_id = ?2", nativeQuery = true)
    List<WorkoutRecord> findByDate(Date formattedDate, int id);

}
