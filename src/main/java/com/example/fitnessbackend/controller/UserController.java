package com.example.fitnessbackend.controller;

import com.example.fitnessbackend.dto.ResponseDTO;
import com.example.fitnessbackend.dto.UserDTO;
import com.example.fitnessbackend.dto.WorkOutPlanDTO;
import com.example.fitnessbackend.service.UserService;
import com.example.fitnessbackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @TimeStamp 2024-01-08 23:17
 * @ProjectDetails fitness-backend
 */
@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private UserService userService;


    @PostMapping(value = "/registration")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        try {
            System.out.println(userDTO);
            int res = userService.saveUser(userDTO);
            if (res == 201) {
                responseDTO.setCode(VarList.Created);
                responseDTO.setMessage("success");
                responseDTO.setData(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            } else if (res == 406) {
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


    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody UserDTO userDTO) {
        if (userService.isValidUserCredentials(userDTO.getEmail(), userDTO.getPassword())) {
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("success");
            responseDTO.setData(userDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } else {
            responseDTO.setCode(VarList.Unauthorized);
            responseDTO.setMessage("Invalid Credential");
            responseDTO.setData(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO userDTO) {
        System.out.println("update");
        System.out.println(userDTO);
        try {
            int res = userService.updateUser(userDTO);
            if (res == 201) {
                responseDTO.setCode(VarList.Created);
                responseDTO.setMessage("success");
                responseDTO.setData(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            } else if (res == 406) {
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

    @GetMapping("/getOneUser")
    public ResponseEntity<ResponseDTO> searchUser(@RequestParam String email) {
        try {
            UserDTO userDTO = userService.searchUser(email);
            if (userDTO == null) {
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(userDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getOneUser/{id}")
    public ResponseEntity<ResponseDTO> searchUserOne(@PathVariable String id) {
        try {
            UserDTO userDTO = userService.searchUserOne(id);
            if (userDTO == null) {
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(userDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        try {
            List<UserDTO> userDTO = userService.getAllUsers();
            System.out.println(userDTO);
            if (userDTO == null) {
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(userDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteTrainer(@PathVariable String id) {
        System.out.println("delete");
        System.out.println(id);
        try {
            int res = userService.deleteUser(id);
            if (res == 201) {
                responseDTO.setCode(VarList.No_Content);
                responseDTO.setMessage("success");
                responseDTO.setData(id);
                return new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
            } else if (res == 406) {
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


    @GetMapping("/searchUserByName")
    public ResponseEntity<ResponseDTO> searchUsersByName(@RequestParam String partialName) {
        System.out.println(partialName);
        try {
            List<UserDTO> userDTOS = userService.searchUsersByName(partialName);

            if (userDTOS.isEmpty()) {
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


 /* @GetMapping("/searchUserByAge/{age}")
    public ResponseEntity<ResponseDTO> searchUsersByAge(@PathVariable String age) {

        try {
            List<UserDTO> userDTOS = userService.searchUsersByAge(age);

            if (userDTOS.isEmpty()) {
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
    }*/


//    @GetMapping("/generateNextUserId")
//    public ResponseEntity<String> generateNextUserId() {
//        String nextUserId = userService.generateNextUserId();
//        return new ResponseEntity<>(nextUserId, HttpStatus.OK);
//    }
}
