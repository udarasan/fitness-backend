package com.example.fitnessbackend.controller;

import com.example.fitnessbackend.dto.AdminDTO;
import com.example.fitnessbackend.dto.ResponseDTO;
import com.example.fitnessbackend.dto.TrainerDTO;
import com.example.fitnessbackend.service.AdminService;
import com.example.fitnessbackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @TimeStamp 2024-01-09 10:42
 * @ProjectDetails fitness-backend
 */
@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginAdmin(@RequestBody AdminDTO adminDTO) {
        if (adminService.isValidTrainerCredentials(adminDTO.getEmail(), adminDTO.getPassword())) {
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("success");
            responseDTO.setData(adminDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } else {
            responseDTO.setCode(VarList.Bad_Gateway);
            responseDTO.setMessage("Invalid Credential");
            responseDTO.setData(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);
        }
    }
}
