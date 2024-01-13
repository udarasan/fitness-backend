package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.MealPlanDTO;
import com.example.fitnessbackend.entity.MealPlan;
import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.repo.MealPlanRepository;
import com.example.fitnessbackend.util.VarList;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @TimeStamp 2024-01-10 18:10
 * @ProjectDetails fitness-backend
 */
@Service
public class MealPlanService {
    @Autowired
    private MealPlanRepository mealPlanRepository;
    @Autowired
    private ModelMapper modelMapper;

    public int saveMealPlan(MealPlanDTO mealPlanDTO) {
        mealPlanRepository.save(modelMapper.map(mealPlanDTO, MealPlan.class));
        return VarList.Created;
    }
    public List<MealPlanDTO> getAllMealPlans() {
        List<MealPlan> mealPlans=mealPlanRepository.findAll();
        return modelMapper.map(mealPlans, new TypeToken<ArrayList<MealPlanDTO>>() {
        }.getType());
    }

    public int  updateMealPlan(MealPlanDTO mealPlanDTO) {
        System.out.println(mealPlanDTO.getMid());
        MealPlan existingEntity = mealPlanRepository.findById(mealPlanDTO.getMid())
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + mealPlanDTO.getMid()));


        existingEntity.setMID(mealPlanDTO.getMid());
        existingEntity.setPlanName(mealPlanDTO.getPlanName());
        existingEntity.setPlanDetails(mealPlanDTO.getPlanDetails());
        existingEntity.setCalorieCount(mealPlanDTO.getCalorieCount());
        // Update other fields as needed

         mealPlanRepository.save(existingEntity);

         return VarList.Created;


    }

    public int deleteMealPlan(Integer id) {
        System.out.println(id);
        Integer existingEntity = mealPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: "+id)).getMID();

        mealPlanRepository.deleteById(existingEntity);
        return VarList.Created;

    }

//    public int deleteMealPlan(MealPlanDTO mealPlanDTO) {
//
//        MealPlan existingEntity = mealPlanRepository.findById(mealPlanDTO.getMid())
//                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: "+mealPlanDTO.getMid() ));
//
//        mealPlanRepository.delete(existingEntity);
//        return VarList.Created;
//    }
}
