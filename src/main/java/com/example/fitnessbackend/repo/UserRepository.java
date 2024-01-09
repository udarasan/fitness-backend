package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @TimeStamp 2024-01-08 23:24
 * @ProjectDetails fitness-backend
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    int deleteByEmail(String email);

    User findByEmailAndPassword(String email, String password);


}
