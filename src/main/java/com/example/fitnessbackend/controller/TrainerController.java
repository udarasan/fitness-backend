package com.example.fitnessbackend.controller;

import com.example.fitnessbackend.dto.*;
import com.example.fitnessbackend.service.MealPlanService;
import com.example.fitnessbackend.service.TrainerService;
import com.example.fitnessbackend.service.UserService;
import com.example.fitnessbackend.service.WorkOutPlanService;
import com.example.fitnessbackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @TimeStamp 2024-01-09 07:34
 * @ProjectDetails fitness-backend
 */
@RestController
@RequestMapping("/api/v1/trainer")
@CrossOrigin(origins = "*")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private WorkOutPlanService workoutService;
    @Autowired
    private UserService userService;
    @Autowired
    private MealPlanService mealPlanService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginTrainer(@RequestBody TrainerDTO trainerDTO) {
        if (trainerService.isValidTrainerCredentials(trainerDTO.getEmail(), trainerDTO.getPassword())) {
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("success");
            responseDTO.setData(trainerDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } else {
            responseDTO.setCode(VarList.Bad_Gateway);
            responseDTO.setMessage("Invalid Credential");
            responseDTO.setData(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);
        }
    }
//    @CrossOrigin(origins = "http://localhost:63343")
    @PostMapping(value = "/registration")
    public ResponseEntity<ResponseDTO> registerTrainer(@RequestBody TrainerDTO trainerDTO) {
        try {
            int res = trainerService.saveTrainer(trainerDTO);
            if (res==201) {
                responseDTO.setCode(VarList.Created);
                responseDTO.setMessage("success");
                responseDTO.setData(trainerDTO);
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
    @GetMapping(value = "/getAllTrainers")
    public ResponseEntity<ResponseDTO> getAllTrainers() {
        try {
            List<TrainerDTO> trainerDTOS = trainerService.getAllTrainers();
            System.out.println(trainerDTOS);
            if(trainerDTOS==null){
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(trainerDTOS);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateTrainer(@RequestBody TrainerDTO trainerDTO) {
        System.out.println("update");
        System.out.println(trainerDTO);
        try {
            int res = trainerService.updateTrainer(trainerDTO);
            if (res==201) {
                responseDTO.setCode(VarList.No_Content);
                responseDTO.setMessage("success");
                responseDTO.setData(trainerDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
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

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteTrainer(@PathVariable String id) {
        System.out.println("delete");
        System.out.println(id);
        try {
            int res = trainerService.deleteTrainer(id);
            if (res==201) {
                responseDTO.setCode(VarList.No_Content);
                responseDTO.setMessage("success");
                responseDTO.setData(id);
                return new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
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


    @GetMapping("/getOneTrainer/{id}")
    public ResponseEntity<ResponseDTO> searchClentWithTrainer(@PathVariable String id) {
        try {
            List<UserDTO> userDTOS = trainerService.searchClentWithTrainer(id);
            System.out.println(userDTOS);
            if(userDTOS==null){
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(userDTOS);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOneTrainer")
    public ResponseEntity<ResponseDTO> searchUser(@RequestParam String email) {
        try {
            TrainerDTO trainerDTO = trainerService.searchTrainer(email);
            System.out.println(trainerDTO);
            if(trainerDTO==null){
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(trainerDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PostMapping(value = "/assignNewWorkout")
    public ResponseEntity<ResponseDTO> assignNewWorkout(@RequestBody AssignNewWorkoutDTO assignNewWorkoutDTO) {
        try {
            WorkOutPlanDTO workOutPlanDTO = assignNewWorkoutDTO.getWorkOutPlanDTO();
            UserDTO userDTO = assignNewWorkoutDTO.getUserDTO();

            System.out.println("@@@"+workOutPlanDTO);
            System.out.println("###"+userDTO);

            Map<String, Object> result = workoutService.saveWorkOutPlan(workOutPlanDTO);
            int res1 = (Integer)result.get("res");
            int newWorkId = (Integer)result.get("savedId");

            userDTO.setWorkout_id(newWorkId);
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


    @Transactional
    @PostMapping(value = "/assignNewMealPlan")
    public ResponseEntity<ResponseDTO> assignNewMealPlan(@RequestBody AssignNewMealPlanDTO assignNewMealPlanDTO) {
        try {
            MealPlanDTO mealPlanDTO = assignNewMealPlanDTO.getMealPlanDTO();
            UserDTO userDTO = assignNewMealPlanDTO.getUserDTO();

            System.out.println("@@@"+mealPlanDTO);
            System.out.println("###"+userDTO);

            Map<String, Object> result = mealPlanService.saveMealPlan(mealPlanDTO);
            int res1 = (Integer)result.get("res");
            int newMealPlanId = (Integer)result.get("savedId");

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

    @GetMapping("/searchTrainerByName")
    public ResponseEntity<ResponseDTO> searchTrainerByName(@RequestParam String partialName) {
        System.out.println(partialName);
        try {
            List<TrainerDTO> trainerDTO = trainerService.searchTrainerByName(partialName);


            if (trainerDTO.isEmpty()) {
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);
            }

            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(trainerDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
