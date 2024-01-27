package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.FitnessGoal;
import com.example.fitnessbackend.entity.MealRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FitnessGoalsRepository extends JpaRepository<FitnessGoal,Integer> {

    @Query(value = "SELECT * FROM fitness_goal WHERE user_id = ?1", nativeQuery = true)
    List<FitnessGoal> findByUser(int id);

    @Query(value = "SELECT * FROM fitness_goal WHERE status = ?1 AND user_id = ?2", nativeQuery = true)
    List<FitnessGoal> findByStatusAndUserId(String status, Integer userId);

    @Query(value = "SELECT * FROM fitness_goal WHERE goal_name LIKE ?1% AND user_id = ?2", nativeQuery = true)
    List<FitnessGoal> findByGoalNameStartingWith(String partialName, Integer userId);
}
