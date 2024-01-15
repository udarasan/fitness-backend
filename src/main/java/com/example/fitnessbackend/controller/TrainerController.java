package com.example.fitnessbackend.controller;

import com.example.fitnessbackend.dto.ResponseDTO;
import com.example.fitnessbackend.dto.TrainerDTO;
import com.example.fitnessbackend.dto.UserDTO;
import com.example.fitnessbackend.service.TrainerService;
import com.example.fitnessbackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
