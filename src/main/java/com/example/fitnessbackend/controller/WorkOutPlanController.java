package com.example.fitnessbackend.controller;

import com.example.fitnessbackend.dto.ResponseDTO;
import com.example.fitnessbackend.dto.TrainerDTO;
import com.example.fitnessbackend.dto.WorkOutPlanDTO;
import com.example.fitnessbackend.service.WorkOutPlanService;
import com.example.fitnessbackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @TimeStamp 2024-01-10 18:08
 * @ProjectDetails fitness-backend
 */
@RestController
@RequestMapping("/api/v1/workoutplan")
@CrossOrigin(origins = "*")
public class WorkOutPlanController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private WorkOutPlanService workOutPlanService;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> saveWorkOutPlan(@RequestBody WorkOutPlanDTO workOutPlanDTO) {
        try {
            Map<String, Object> result = workOutPlanService.saveWorkOutPlan(workOutPlanDTO);
            int res = (Integer)result.get("res");
            if (res==201) {
                responseDTO.setCode(VarList.Created);
                responseDTO.setMessage("success");
                responseDTO.setData(workOutPlanDTO);
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

    @PostMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateTrainer(@RequestBody WorkOutPlanDTO workOutPlanDTO) {
        System.out.println("update");
        try {
            int res = workOutPlanService.updateWorkOut(workOutPlanDTO);
            if (res==201) {
                responseDTO.setCode(VarList.Created);
                responseDTO.setMessage("success");
                responseDTO.setData(workOutPlanDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            } else if (res==406) {
                responseDTO.setCode(VarList.Not_Acceptable);
                responseDTO.setMessage("Workout Not Available");
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

    @GetMapping(value = "/getAllWorkOutPlans")
    public ResponseEntity<ResponseDTO> getAllWorkOutPlans() {
        try {
            List<WorkOutPlanDTO> allWorkOutPlans = workOutPlanService.getAllWorkOutPlans();
            if(allWorkOutPlans==null){
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(allWorkOutPlans);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/plansByPartName")
    public ResponseEntity<ResponseDTO> searchUsers(@RequestParam String partialName) {
        System.out.println(partialName);
        try {
            List<WorkOutPlanDTO> workOutDTOs = workOutPlanService.searchWorkouts(partialName);

            if (workOutDTOs.isEmpty()) {
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);
            }

            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(workOutDTOs);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteWorkout(@PathVariable String id) {
        System.out.println("id");
        try {
            int res = workOutPlanService.deleteWorkout(id);
            if (res==201) {
                responseDTO.setCode(VarList.No_Content);
                responseDTO.setMessage("success");
                responseDTO.setData(id);
                return new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
            } else if (res==406) {
                responseDTO.setCode(VarList.Not_Acceptable);
                responseDTO.setMessage("Workout Not Found");
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
}
