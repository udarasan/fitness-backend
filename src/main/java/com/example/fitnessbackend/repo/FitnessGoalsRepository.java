package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.FitnessGoal;
import com.example.fitnessbackend.entity.MealRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FitnessGoalsRepository extends JpaRepository<FitnessGoal,Integer> {

    @Query(value = "SELECT * FROM fitness_goal WHERE user_id = ?1", nativeQuery = true)
    List<FitnessGoal> findByUser(int id);

    List<FitnessGoal> findByStatus(String status);

    List<FitnessGoal> findByGoalNameStartingWith(String partialName);
}
