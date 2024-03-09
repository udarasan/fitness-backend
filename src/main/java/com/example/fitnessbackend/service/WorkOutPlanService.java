package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.MealPlanDTO;
import com.example.fitnessbackend.dto.SavedIdDTO;
import com.example.fitnessbackend.dto.WorkOutPlanDTO;
import com.example.fitnessbackend.entity.MealPlan;
import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.entity.WorkOutPlan;
import com.example.fitnessbackend.repo.WorkOutPlanRepository;
import com.example.fitnessbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @TimeStamp 2024-01-10 18:10
 * @ProjectDetails fitness-backend
 */
@Service
public class WorkOutPlanService {
    @Autowired
    private WorkOutPlanRepository workOutPlanRepository;
    @Autowired
    private ModelMapper modelMapper;


    public SavedIdDTO saveWorkOutPlan(WorkOutPlanDTO workOutPlanDTO) {
        WorkOutPlan workOutPlan = workOutPlanRepository.save(modelMapper.map(workOutPlanDTO, WorkOutPlan.class));

        int generatedId = workOutPlan.getWID();
        SavedIdDTO result = new SavedIdDTO(VarList.Created, generatedId);
        return result;
    }

    public List<WorkOutPlanDTO> getAllWorkOutPlans() {
        List<WorkOutPlan> mealPlans=workOutPlanRepository.findAll();
        return modelMapper.map(mealPlans, new TypeToken<ArrayList<WorkOutPlanDTO>>() {
        }.getType());
    }

    public List<WorkOutPlanDTO> searchWorkouts(String partialName) {
        List<WorkOutPlan> plans = workOutPlanRepository.findByPlanNameStartingWith(partialName);

        List<WorkOutPlanDTO> workOutPlanDTOs = plans.stream()
                .map(plan -> modelMapper.map(plan, WorkOutPlanDTO.class))
                .collect(Collectors.toList());

        return workOutPlanDTOs;
    }

    public int updateWorkOut(WorkOutPlanDTO workOutPlanDTO) {
        if (!workOutPlanRepository.existsById(workOutPlanDTO.getWid())) {
            return VarList.Not_Acceptable;
        } else {
            workOutPlanRepository.save(modelMapper.map(workOutPlanDTO, WorkOutPlan.class));
            return VarList.Created;
        }
    }

    public int deleteWorkout(String id) {
        if (!workOutPlanRepository.existsById(Integer.valueOf(id))) {
            return VarList.Not_Acceptable;
        } else {
            workOutPlanRepository.deleteById(Integer.valueOf(id));
            return VarList.Created;
        }
    }

    public int getNumberOfWorkouts() {
        int count = (int) workOutPlanRepository.count();
        return count;
    }

    public WorkOutPlanDTO searchWorkOutPlan(String id) {
        if (workOutPlanRepository.existsById(Integer.valueOf(id))) {
            Optional<WorkOutPlan> workOutPlan = workOutPlanRepository.findById(Integer.valueOf(id));
            return modelMapper.map(workOutPlan, WorkOutPlanDTO.class);
        } else {
            return null;
        }
    }
}
