package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.AdminDTO;
import com.example.fitnessbackend.dto.TrainerDTO;
import com.example.fitnessbackend.entity.Admin;
import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.repo.AdminRepository;
import com.example.fitnessbackend.repo.TrainerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.matcher.ModifierMatcher;
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

    @Autowired
    private ModelMapper modelMapper;

    public boolean isValidAdminCredentials(String email, String password) {
        Admin  admin= adminRepository.findByEmailAndPassword(email, password);
        return admin != null;
    }

    public AdminDTO searchAdmin(String email) {

        if (adminRepository.existsByEmail(email)) {
            Admin admin=adminRepository.findByEmail(email);
            return modelMapper.map(admin,AdminDTO.class);
        } else {
            return null;
        }
    }
}
