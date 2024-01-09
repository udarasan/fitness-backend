package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @TimeStamp 2024-01-09 07:36
 * @ProjectDetails fitness-backend
 */
@Repository
public interface TrainerRepository  extends JpaRepository<Trainer,Integer> {
    Trainer findByEmailAndPassword(String username, String password);
    boolean existsByEmail(String email);

}
