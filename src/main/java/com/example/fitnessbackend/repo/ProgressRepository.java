package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.Chat;
import com.example.fitnessbackend.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress,Integer> {

    @Query(value = "SELECT * FROM progress WHERE user_id = ?1", nativeQuery = true)
    List<Progress> findByProgress( int userId);

}
