package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.MealPlanDTO;
import com.example.fitnessbackend.entity.MealPlan;
import com.example.fitnessbackend.repo.MealPlanRepository;
import com.example.fitnessbackend.util.VarList;
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
}
