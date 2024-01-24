package com.example.fitnessbackend.repo;


import com.example.fitnessbackend.entity.Chat;
import com.example.fitnessbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer> {

    @Query(value = "SELECT * FROM chat WHERE trainer_id = ?1 AND user_id = ?2", nativeQuery = true)
    List<Chat> findByChat(int trainerId, int userId);

}
