package com.example.fitnessbackend.service;

import com.example.fitnessbackend.entity.Admin;
import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.repo.AdminRepository;
import com.example.fitnessbackend.repo.TrainerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @TimeStamp 2024-01-09 10:42
 * @ProjectDetails fitness-backend
 */
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public boolean isValidTrainerCredentials(String email, String password) {
        Admin  admin= adminRepository.findByEmailAndPassword(email, password);
        return admin != null;
    }
}
