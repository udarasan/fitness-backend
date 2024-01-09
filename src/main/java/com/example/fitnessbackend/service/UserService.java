package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.UserDTO;
import com.example.fitnessbackend.entity.User;
import com.example.fitnessbackend.repo.UserRepository;
import com.example.fitnessbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @TimeStamp 2024-01-08 23:24
 * @ProjectDetails fitness-backend
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ModelMapper modelMapper;



    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }
    public boolean isValidUserCredentials(String username, String password) {
        User user = userRepository.findByEmailAndPassword(username, password);
        return user != null;
    }



    public List<UserDTO> getAllUsers() {
        List<User> users=userRepository.findAll();
        return modelMapper.map(users, new TypeToken<ArrayList<UserDTO>>() {
        }.getType());
    }

    public UserDTO searchUser(String email) {
        if (userRepository.existsByEmail(email)) {
            User user=userRepository.findByEmail(email);
            return modelMapper.map(user,UserDTO.class);
        } else {
            return null;
        }
    }

    public int updateUser(UserDTO userDTO) {
        if (!userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }
}
