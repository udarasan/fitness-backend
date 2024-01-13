package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.WorkOutPlanDTO;
import com.example.fitnessbackend.entity.WorkOutPlan;
import com.example.fitnessbackend.repo.WorkOutPlanRepository;
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
public class WorkOutPlanService {
    @Autowired
    private WorkOutPlanRepository workOutPlanRepository;
    @Autowired
    private ModelMapper modelMapper;


    public int saveWorkOutPlan(WorkOutPlanDTO workOutPlanDTO) {
        workOutPlanRepository.save(modelMapper.map(workOutPlanDTO, WorkOutPlan.class));
        return VarList.Created;
    }

    public List<WorkOutPlanDTO> getAllWorkOutPlans() {
        List<WorkOutPlan> mealPlans=workOutPlanRepository.findAll();
        return modelMapper.map(mealPlans, new TypeToken<ArrayList<WorkOutPlanDTO>>() {
        }.getType());
    }
}
