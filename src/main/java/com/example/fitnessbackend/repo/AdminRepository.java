package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.Admin;
import com.example.fitnessbackend.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @TimeStamp 2024-01-09 10:42
 * @ProjectDetails fitness-backend
 */
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByEmailAndPassword(String username, String password);
}
