package com.example.fitnessbackend.controller;

import com.example.fitnessbackend.dto.EquipmentDTO;
import com.example.fitnessbackend.dto.ProgressDTO;
import com.example.fitnessbackend.dto.ResponseDTO;
import com.example.fitnessbackend.service.EquipmentService;
import com.example.fitnessbackend.service.ProgressService;
import com.example.fitnessbackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/progress")
@CrossOrigin(origins = "*")
public class ProgressController {
    @Autowired
    private ProgressService progressService;

    @Autowired
    private ResponseDTO responseDTO;
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> saveProgress(@RequestBody ProgressDTO progressDTO) {
        try {
            System.out.println(progressDTO);
            int res = progressService.saveProgress(progressDTO);
            if (res==201) {
                responseDTO.setCode(VarList.Created);
                responseDTO.setMessage("success");
                responseDTO.setData(progressDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            } else if (res==406) {
                responseDTO.setCode(VarList.Not_Acceptable);

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


    @GetMapping(value = "/getAllProgress/{id}")
    public ResponseEntity<ResponseDTO> getAllProgress(@PathVariable int id) {
        System.out.println(id);
        try {
           List<ProgressDTO> progressDTOS = progressService.getAllProgress(id);
            System.out.println(progressDTOS);
            if(progressDTOS==null){
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(progressDTOS);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {

            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateProgress(@RequestBody ProgressDTO progressDTO) {
        System.out.println("update");
        System.out.println(progressDTO);
        try {
            int res = progressService.updateProgress(progressDTO);
            if (res==201) {
                responseDTO.setCode(VarList.No_Content);
                responseDTO.setMessage("success");
                responseDTO.setData(progressDTO);
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
    public ResponseEntity<ResponseDTO> deleteProgress(@PathVariable String id) {
        System.out.println("delete");
        System.out.println(id);
        try {
            int res = progressService.deleteProgress(id);
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

}
