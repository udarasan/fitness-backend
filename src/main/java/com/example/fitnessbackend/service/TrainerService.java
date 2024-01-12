package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.TrainerDTO;
import com.example.fitnessbackend.dto.UserDTO;
import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.entity.User;
import com.example.fitnessbackend.repo.TrainerRepository;
import com.example.fitnessbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @TimeStamp 2024-01-09 07:37
 * @ProjectDetails fitness-backend
 */
@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public boolean isValidTrainerCredentials(String username, String password) {
        Trainer trainer = trainerRepository.findByEmailAndPassword(username, password);
        return trainer != null;
    }

    public List<TrainerDTO> getAllTrainers() {
        List<Trainer> users=trainerRepository.findAll();
        System.out.println(users);
        return modelMapper.map(users, new TypeToken<ArrayList<TrainerDTO>>() {
        }.getType());

    }

    public int saveTrainer(TrainerDTO trainerDTO) {
        if (trainerRepository.existsByEmail(trainerDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            trainerRepository.save(modelMapper.map(trainerDTO, Trainer.class));
            return VarList.Created;
        }
    }
}
