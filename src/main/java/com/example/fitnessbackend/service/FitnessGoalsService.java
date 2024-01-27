package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.FitnessGoalDTO;
import com.example.fitnessbackend.dto.MealRecordDTO;
import com.example.fitnessbackend.dto.WorkOutPlanDTO;
import com.example.fitnessbackend.entity.FitnessGoal;
import com.example.fitnessbackend.entity.MealRecord;
import com.example.fitnessbackend.entity.User;
import com.example.fitnessbackend.entity.WorkOutPlan;
import com.example.fitnessbackend.repo.FitnessGoalsRepository;
import com.example.fitnessbackend.repo.UserRepository;
import com.example.fitnessbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FitnessGoalsService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FitnessGoalsRepository fitnessGoalsRepository;

    public int saveGoal(FitnessGoalDTO fitnessGoalDTO) {
        User user = userRepository.findById(fitnessGoalDTO.getUserId()).orElse(null);

        FitnessGoal fitnessGoal = modelMapper.map(fitnessGoalDTO, FitnessGoal.class);

        fitnessGoal.setUser(user);

        fitnessGoalsRepository.save(fitnessGoal);
        return VarList.Created;
    }

    public List<FitnessGoalDTO> getAllGoals(int id) {
        List<FitnessGoal> fitnessGoals = fitnessGoalsRepository.findByUser(id);

        List<FitnessGoalDTO> fitnessGoalDTOList = new ArrayList<>();

        for (FitnessGoal fitnessGoal : fitnessGoals) {
            FitnessGoalDTO fitnessGoalDTO = modelMapper.map(fitnessGoal, FitnessGoalDTO.class);
            fitnessGoalDTOList.add(fitnessGoalDTO);
        }
        return fitnessGoalDTOList;
    }

    public int updateGoal(FitnessGoalDTO fitnessGoalDTO) {
        if (!fitnessGoalsRepository.existsById(fitnessGoalDTO.getGoalId())) {
            return VarList.Not_Acceptable;
        } else {
            User user = userRepository.findById(fitnessGoalDTO.getUserId()).orElse(null);

            FitnessGoal fitnessGoal = modelMapper.map(fitnessGoalDTO, FitnessGoal.class);

            fitnessGoal.setUser(user);

            fitnessGoalsRepository.save(fitnessGoal);
            return VarList.Created;
        }
    }

    public int deleteGoal(String id) {
        if (!fitnessGoalsRepository.existsById(Integer.valueOf(id))) {
            return VarList.Not_Acceptable;
        } else {
            fitnessGoalsRepository.deleteById(Integer.valueOf(id));
            return VarList.Created;
        }
    }

    public List<FitnessGoalDTO> searchByStatus(String status, int id) {
        List<FitnessGoal> goals = fitnessGoalsRepository.findByStatusAndUserId(status, id);

        List<FitnessGoalDTO> fitnessGoalDTOS = goals.stream()
                .map(goal -> modelMapper.map(goal, FitnessGoalDTO.class))
                .collect(Collectors.toList());

        return fitnessGoalDTOS;
    }

    public List<FitnessGoalDTO> searchByName(String partialName, int id) {
        List<FitnessGoal> goals = fitnessGoalsRepository.findByGoalNameStartingWith(partialName,id);

        List<FitnessGoalDTO> fitnessGoalDTOS = goals.stream()
                .map(goal -> modelMapper.map(goal, FitnessGoalDTO.class))
                .collect(Collectors.toList());

        return fitnessGoalDTOS;
    }
}
