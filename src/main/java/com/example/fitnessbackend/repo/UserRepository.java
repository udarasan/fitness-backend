package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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


    List<User> findByNameStartingWith(String partialName);


    @Query(value = "select * from user where trainer_id =?1",nativeQuery = true)

    List<User> findAllByClient(int id);

//    @Query(value = "select * from user where age =?1",nativeQuery = true)
//
//    List<User> findByAge(String age);
}
