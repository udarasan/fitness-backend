package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.MealRecord;
import com.example.fitnessbackend.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MealRecordRepository extends JpaRepository<MealRecord,Integer> {

    @Query(value = "SELECT * FROM meal_record WHERE user_id = ?1", nativeQuery = true)
    List<MealRecord> findByUser(int id);

    @Query(value = "SELECT * FROM meal_record WHERE date = ?1 AND user_id = ?2", nativeQuery = true)
    List<MealRecord> findByDate(Date date, int id);
}
