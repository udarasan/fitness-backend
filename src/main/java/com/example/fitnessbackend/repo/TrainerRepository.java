package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @TimeStamp 2024-01-09 07:36
 * @ProjectDetails fitness-backend
 */
@Repository
public interface TrainerRepository  extends JpaRepository<Trainer,Integer> {
    Trainer findByEmailAndPassword(String username, String password);
    boolean existsByEmail(String email);


    Trainer findByEmail(String email);

    List<Trainer> findAllByEmail(String email);

    List<Trainer> findByNameStartingWith(String partialName);
    @Query(value = "select * from trainer where status =?1",nativeQuery = true)

    List<Trainer> findByStatus(String status);

    int countByStatus(String status);
//    @Query(value = "select * from trainer where category =?1",nativeQuery = true)
//
//    List<Trainer> findByTrainerWithCategory(String category);
}
