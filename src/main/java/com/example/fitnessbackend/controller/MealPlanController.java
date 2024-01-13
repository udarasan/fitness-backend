package com.example.fitnessbackend.controller;

import com.example.fitnessbackend.dto.MealPlanDTO;
import com.example.fitnessbackend.dto.ResponseDTO;
import com.example.fitnessbackend.service.MealPlanService;
import com.example.fitnessbackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @TimeStamp 2024-01-10 18:08
 * @ProjectDetails fitness-backend
 */
@RestController
@RequestMapping("/api/v1/mealPlan")
@CrossOrigin(origins = "*")
public class MealPlanController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private MealPlanService mealPlanService;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> saveMealPlan(@RequestBody MealPlanDTO mealPlanDTO) {
        try {
            int res = mealPlanService.saveMealPlan(mealPlanDTO);
            if (res==201) {
                responseDTO.setCode(VarList.Created);
                responseDTO.setMessage("success");
                responseDTO.setData(mealPlanDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            } else if (res==406) {
                responseDTO.setCode(VarList.Not_Acceptable);
                responseDTO.setMessage("Email Already Use");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
            } else {
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("Error");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllMealPlans")
    public ResponseEntity<ResponseDTO> getAllMealPlans() {
        try {
            List<MealPlanDTO> mealPlanDTOS = mealPlanService.getAllMealPlans();
            if(mealPlanDTOS==null){
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(mealPlanDTOS);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
