package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @TimeStamp 2024-01-09 10:42
 * @ProjectDetails fitness-backend
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByEmailAndPassword(String username, String password);
}
