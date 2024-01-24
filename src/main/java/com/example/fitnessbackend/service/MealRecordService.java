package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.MealRecordDTO;
import com.example.fitnessbackend.entity.MealRecord;
import com.example.fitnessbackend.entity.Progress;
import com.example.fitnessbackend.entity.User;
import com.example.fitnessbackend.repo.MealRecordRepository;
import com.example.fitnessbackend.repo.UserRepository;
import com.example.fitnessbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealRecordService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MealRecordRepository mealRecordRepository;

    public int saveRecord(MealRecordDTO mealRecordDTO) {
        User user = userRepository.findById(mealRecordDTO.getUserId()).orElse(null);

        MealRecord mealRecord = modelMapper.map(mealRecordDTO, MealRecord.class);

        mealRecord.setUser(user);

        mealRecordRepository.save(mealRecord);
        return VarList.Created;
    }

    public List<MealRecordDTO> getAllMealRecords(String id) {
    }
}
