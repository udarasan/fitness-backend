package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.WorkOutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @TimeStamp 2024-01-10 18:10
 * @ProjectDetails fitness-backend
 */
@Repository
public interface WorkOutPlanRepository extends JpaRepository<WorkOutPlan,Integer> {

    List<WorkOutPlan> findByPlanNameStartingWith(String partialEmail);
}
