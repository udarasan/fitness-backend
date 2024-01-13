package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @TimeStamp 2024-01-10 18:10
 * @ProjectDetails fitness-backend
 */
@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan,Integer> {

}