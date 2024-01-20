package com.example.fitnessbackend.service;


import com.example.fitnessbackend.dto.ChatDTO;
import com.example.fitnessbackend.dto.ProgressDTO;
import com.example.fitnessbackend.entity.Chat;
import com.example.fitnessbackend.entity.Progress;
import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.entity.User;
import com.example.fitnessbackend.repo.ChatRepository;
import com.example.fitnessbackend.repo.TrainerRepository;
import com.example.fitnessbackend.repo.UserRepository;
import com.example.fitnessbackend.util.VarList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;
    public int saveMessages(ChatDTO chatDTO) {

        if (chatRepository.existsById(chatDTO.getChatId())) {
            return VarList.Not_Acceptable;
        } else {
            Trainer trainer = trainerRepository.findById(chatDTO.getTrainer_id()).orElse(null);
            User user = userRepository.findById(chatDTO.getUser_id()).orElse(null);

            // Map UserDTO to User entity
            Chat chat = modelMapper.map(chatDTO, Chat.class);

            // Set the Trainer for the User
            chat.setTrainer(trainer);
            chat.setUser(user);

            // Save the User entity
            chatRepository.save(chat);
            return VarList.Created;
        }
    }

    public List<ChatDTO> getAllChats(String trainerId, String userId) {
        String hql = "FROM Chat c WHERE c.trainer.id = :trainerId AND c.user.id = :userId";
        List<Chat> chats = entityManager.createQuery(hql, Chat.class)
                .setParameter("trainerId", trainerId)
                .setParameter("userId", userId)
                .getResultList();

        List<ChatDTO> chatDTOS = new ArrayList<>();

        for (Chat chat : chats) {
            ChatDTO chatDTO = modelMapper.map(chat, ChatDTO.class);
            // Additional mapping or processing if needed
            chatDTOS.add(chatDTO);
        }

        return chatDTOS;
    }
}
