package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends JpaRepository<Progress,Integer> {



}
