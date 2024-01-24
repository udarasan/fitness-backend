package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.MealRecord;
import com.example.fitnessbackend.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRecordRepository extends JpaRepository<MealRecord,Integer> {

}
