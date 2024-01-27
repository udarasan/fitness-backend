package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.MealRecordDTO;
import com.example.fitnessbackend.dto.WorkOutRecordDTO;
import com.example.fitnessbackend.entity.MealRecord;
import com.example.fitnessbackend.entity.User;
import com.example.fitnessbackend.entity.WorkoutRecord;
import com.example.fitnessbackend.repo.UserRepository;
import com.example.fitnessbackend.repo.WorkOutRecordRepository;
import com.example.fitnessbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkOutRecordService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkOutRecordRepository workOutRecordRepository;


    public int saveWorkOutRecord(WorkOutRecordDTO workOutRecordDTO) {
        User user = userRepository.findById(workOutRecordDTO.getUserId()).orElse(null);

        WorkoutRecord workoutRecord = modelMapper.map(workOutRecordDTO, WorkoutRecord.class);

        workoutRecord.setUser(user);

        workOutRecordRepository.save(workoutRecord);
        return VarList.Created;
    }

    public List<WorkOutRecordDTO> getAllWorkOutRecords(int id) {

        List<WorkoutRecord> workoutRecords = workOutRecordRepository.findByUser(id);

        List<WorkOutRecordDTO> workOutRecordDTOS = new ArrayList<>();

        for (WorkoutRecord workoutRecord : workoutRecords) {
            WorkOutRecordDTO workOutRecordDTO = modelMapper.map(workoutRecord, WorkOutRecordDTO.class);
            workOutRecordDTOS.add(workOutRecordDTO);
        }
        return workOutRecordDTOS;
    }

    public int updateWorkoutRecord(WorkOutRecordDTO workOutRecordDTO) {
        if (!workOutRecordRepository.existsById(workOutRecordDTO.getWrID())) {
            return VarList.Not_Acceptable;
        } else {
            User user = userRepository.findById(workOutRecordDTO.getUserId()).orElse(null);

            WorkoutRecord workoutRecord = modelMapper.map(workOutRecordDTO, WorkoutRecord.class);

            workoutRecord.setUser(user);

            workOutRecordRepository.save(workoutRecord);
            return VarList.Created;
        }
    }

    public int deleteWorkoutRecord(String id) {
        if (!workOutRecordRepository.existsById(Integer.valueOf(id))) {
            return VarList.Not_Acceptable;
        } else {
            workOutRecordRepository.deleteById(Integer.valueOf(id));
            return VarList.Created;
        }
    }

    public List<WorkOutRecordDTO> searchWorkOutRecords(String date, int id) throws ParseException { SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedDate = dateFormat.parse(date);

        List<WorkoutRecord> plans = workOutRecordRepository.findByDate(formattedDate, id);

        List<WorkOutRecordDTO> workOutRecordDTOS = plans.stream()
                .map(record -> modelMapper.map(record, WorkOutRecordDTO.class))
                .collect(Collectors.toList());

        return workOutRecordDTOS;
    }
    }

