package com.example.fitnessbackend.dto;


import com.example.fitnessbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatDTO {
    private int chatId;
    private String message;
    private boolean userSent;
    private int user_id;
    private int trainer_id;
}
