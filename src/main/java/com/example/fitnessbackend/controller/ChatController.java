package com.example.fitnessbackend.controller;


import com.example.fitnessbackend.dto.ChatDTO;
import com.example.fitnessbackend.dto.EquipmentDTO;
import com.example.fitnessbackend.dto.ResponseDTO;
import com.example.fitnessbackend.service.ChatService;
import com.example.fitnessbackend.service.EquipmentService;
import com.example.fitnessbackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> saveMessages(@RequestBody ChatDTO chatDTO) {
        try {
            int res = chatService.saveMessages(chatDTO);
            if (res==201) {
                responseDTO.setCode(VarList.Created);
                responseDTO.setMessage("success");
                responseDTO.setData(chatDTO);
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

    @GetMapping(value = "/getAllChats/{tid}/{uid}")
    public ResponseEntity<ResponseDTO> getAllChats(@PathVariable int tid, @PathVariable int uid) {
        try {
            List<ChatDTO> chatDTOS = chatService.getAllChats(tid,uid);
            System.out.println(chatDTOS);
            if(chatDTOS==null){
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(chatDTOS);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
