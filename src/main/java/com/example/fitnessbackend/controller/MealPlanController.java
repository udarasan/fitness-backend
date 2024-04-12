package com.example.fitnessbackend.controller;

import com.example.fitnessbackend.dto.*;
import com.example.fitnessbackend.service.MealPlanService;
import com.example.fitnessbackend.service.UserService;
import com.example.fitnessbackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> saveMealPlan(@RequestBody MealPlanDTO mealPlanDTO) {
        try {
            SavedIdDTO result = mealPlanService.saveMealPlan(mealPlanDTO);
            int res = result.getRes();
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

    @PostMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateMealPlan(@RequestBody MealPlanDTO mealPlanDTO) {
        System.out.println("update");
        System.out.println(mealPlanDTO);
        try {
            int res = mealPlanService.updateMealPlan(mealPlanDTO);
            if (res==201) {
                responseDTO.setCode(VarList.Created);
                responseDTO.setMessage("success");
                responseDTO.setData(mealPlanDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            } else if (res==406) {
                responseDTO.setCode(VarList.Not_Acceptable);
                responseDTO.setMessage("Email Not Available ");
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

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteMealPlan(@PathVariable int id) {
        System.out.println("update");
        System.out.println(id);
        try {
            int res = mealPlanService.deleteMealPlan(id);
            if (res==201) {
                responseDTO.setCode(VarList.Created);
                responseDTO.setMessage("success");
                responseDTO.setData(id);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            } else if (res==406) {
                responseDTO.setCode(VarList.Not_Acceptable);
                responseDTO.setMessage("Email Not Available ");
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

    @GetMapping("/searchMealByName")
    public ResponseEntity<ResponseDTO> searchMealByName(@RequestParam String partialName) {
        System.out.println(partialName);
        try {
            List<MealPlanDTO> mealPlanDTOS = mealPlanService.searchMealByName(partialName);

            if (mealPlanDTOS.isEmpty()) {
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

    @Transactional
    @PostMapping(value = "/assignNewMealPlan")
    public ResponseEntity<ResponseDTO> assignNewMealPlan(@RequestBody AssignNewMealPlanDTO assignNewMealPlanDTO) {
        try {
            MealPlanDTO mealPlanDTO = assignNewMealPlanDTO.getMealPlanDTO();
            System.out.println("check "+mealPlanDTO);
            UserDTO userDTO = assignNewMealPlanDTO.getUserDTO();

            SavedIdDTO result = mealPlanService.saveMealPlan(mealPlanDTO);
            int res1 = result.getRes();
            int newMealPlanId = result.getGeneratedId();

            userDTO.setMeal_plan_id(newMealPlanId);
            int res2 = userService.updateUser(userDTO);

            if (res1==201 && res2==201) {
                responseDTO.setCode(VarList.Created);
                responseDTO.setMessage("success");
                responseDTO.setData(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            }  else {
                responseDTO.setCode(VarList.Bad_Request);
                responseDTO.setMessage("Operation failed");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMealPlan/{id}")
    public ResponseEntity<ResponseDTO> searchMealPlan(@PathVariable String id) {
        try {
            MealPlanDTO mealPlanDTO = mealPlanService.searchMealPlan(id);
            System.out.println(mealPlanDTO);
            if(mealPlanDTO==null){
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(mealPlanDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


