package com.example.fitnessbackend.repo;

import com.example.fitnessbackend.entity.Equipment;
import com.example.fitnessbackend.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Integer> {

    List<Equipment> findByequipmentNameStartingWith(String partialName);


  /*  @Query(value = "select * from equipment where MONTH(purchase_date) =?1",nativeQuery = true)
    List<Equipment> findByMonth(String month);*/
}
