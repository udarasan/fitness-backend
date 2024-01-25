package com.example.fitnessbackend.controller;

import com.example.fitnessbackend.dto.AdminDTO;
import com.example.fitnessbackend.dto.ResponseDTO;
import com.example.fitnessbackend.dto.TrainerDTO;
import com.example.fitnessbackend.service.*;
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

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkOutPlanService workoutService;

    @Autowired
    private MealPlanService mealService;

    @Autowired
    private EquipmentService equipmentService;
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

    @GetMapping(value = "/getTrainerCount")
    public ResponseEntity<ResponseDTO> getNumberOfTrainers() {
        try {
            int numberOfTrainers = trainerService.getNumberOfTrainers();

            responseDTO.setCode(VarList.OK);
            responseDTO.setMessage("Success");
            responseDTO.setData(numberOfTrainers);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/getEquipmentCount")
    public ResponseEntity<ResponseDTO> getNumberOfEquipments() {
        try {
            int numberOfEquipments = equipmentService.getNumberOfEquipments();

            responseDTO.setCode(VarList.OK);
            responseDTO.setMessage("Success");
            responseDTO.setData(numberOfEquipments);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getMemberCount")
    public ResponseEntity<ResponseDTO> getNumberOfMembers() {
        try {
            int numberOfMembers = userService.getNumberOfMembers();
            responseDTO.setCode(VarList.OK);
            responseDTO.setMessage("Success");
            responseDTO.setData(numberOfMembers);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getWorkoutPlanCount")
    public ResponseEntity<ResponseDTO> getNumberOfWorkouts() {
        try {
            int numberOfWorkouts = workoutService.getNumberOfWorkouts();
            responseDTO.setCode(VarList.OK);
            responseDTO.setMessage("Success");
            responseDTO.setData(numberOfWorkouts);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getMealPlanCount")
    public ResponseEntity<ResponseDTO> getNumberOfMeals() {
        try {
            int numberOfMeals = mealService.getNumberOfMeals();
            responseDTO.setCode(VarList.OK);
            responseDTO.setMessage("Success");
            responseDTO.setData(numberOfMeals);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
