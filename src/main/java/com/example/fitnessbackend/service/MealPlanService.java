package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.MealPlanDTO;
import com.example.fitnessbackend.dto.SavedIdDTO;
import com.example.fitnessbackend.dto.UserDTO;
import com.example.fitnessbackend.entity.MealPlan;
import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.entity.User;
import com.example.fitnessbackend.repo.MealPlanRepository;
import com.example.fitnessbackend.util.VarList;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public SavedIdDTO saveMealPlan(MealPlanDTO mealPlanDTO) {
      MealPlan mealPlan=  mealPlanRepository.save(modelMapper.map(mealPlanDTO, MealPlan.class));

        int generatedId = mealPlan.getMID();
        SavedIdDTO result = new SavedIdDTO(VarList.Created, generatedId);
        return result;
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

    public int getNumberOfMeals() {
        int count = (int) mealPlanRepository.count();
        return count;
    }

    public List<MealPlanDTO> searchMealByName(String partialName) {
        List<MealPlan> plans = mealPlanRepository.findByPlanNameStartingWith(partialName);

        List<MealPlanDTO> mealPlanDTOS = plans.stream()
                .map(name -> modelMapper.map(name, MealPlanDTO.class))
                .collect(Collectors.toList());

        return mealPlanDTOS;
    }


}
