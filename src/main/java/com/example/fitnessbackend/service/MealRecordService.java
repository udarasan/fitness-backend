package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.MealRecordDTO;
import com.example.fitnessbackend.dto.ProgressDTO;
import com.example.fitnessbackend.entity.MealRecord;
import com.example.fitnessbackend.entity.Progress;
import com.example.fitnessbackend.entity.User;
import com.example.fitnessbackend.repo.MealRecordRepository;
import com.example.fitnessbackend.repo.UserRepository;
import com.example.fitnessbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<MealRecordDTO> getAllMealRecords(int id) {
        List<MealRecord> mealRecords = mealRecordRepository.findByUser(id);

        List<MealRecordDTO> mealRecordDTOList = new ArrayList<>();

        for (MealRecord mealRecord : mealRecords) {
            MealRecordDTO mealRecordDTO = modelMapper.map(mealRecord, MealRecordDTO.class);
            mealRecordDTOList.add(mealRecordDTO);
        }
        return mealRecordDTOList;
    }

    public int updateMealRecord(MealRecordDTO mealRecordDTO) {
        if (!mealRecordRepository.existsById(mealRecordDTO.getMrID())) {
            return VarList.Not_Acceptable;
        } else {
            User user = userRepository.findById(mealRecordDTO.getUserId()).orElse(null);

            MealRecord mealRecord = modelMapper.map(mealRecordDTO, MealRecord.class);

            mealRecord.setUser(user);

            mealRecordRepository.save(mealRecord);
            return VarList.Created;
        }
    }

    public int deleteMealRecord(String id) {
        if (!mealRecordRepository.existsById(Integer.valueOf(id))) {
            return VarList.Not_Acceptable;
        } else {
            mealRecordRepository.deleteById(Integer.valueOf(id));
            return VarList.Created;
        }
    }
}
