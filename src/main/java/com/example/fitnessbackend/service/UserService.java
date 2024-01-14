package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.UserDTO;
import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.entity.User;
import com.example.fitnessbackend.repo.TrainerRepository;
import com.example.fitnessbackend.repo.UserRepository;
import com.example.fitnessbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private TrainerRepository trainerRepository;

    @Autowired
    private ModelMapper modelMapper;



    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            Trainer trainer = trainerRepository.findById(userDTO.getTrainer_id()).orElse(null);

            // Map UserDTO to User entity
            User user = modelMapper.map(userDTO, User.class);

            // Set the Trainer for the User
            user.setTrainer(trainer);

            // Save the User entity
            userRepository.save(user);
            return VarList.Created;
        }
    }
    public boolean isValidUserCredentials(String username, String password) {
        User user = userRepository.findByEmailAndPassword(username, password);
        return user != null;
    }



    public List<UserDTO> getAllUsers() {
//        List<User> users=userRepository.findAll();
//
//        return modelMapper.map(users, new TypeToken<ArrayList<UserDTO>>() {
//        }.getType());
        List<User> users = userRepository.findAll();

        // Configure modelMapper to handle the mapping of trainer_id
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT); // Adjust matching strategy as needed

        Type listType = new TypeToken<ArrayList<UserDTO>>() {}.getType();
        return users.stream()
                .map(user -> {
                    UserDTO userDTO = modelMapper.map(user, UserDTO.class);
                    if (user.getTrainer() != null) {
                        userDTO.setTrainer_id(user.getTrainer().getTID());
                    }
                    return userDTO;
                })
                .collect(Collectors.toList());
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

    public int deleteUser(String id) {

        if (!userRepository.existsById(Integer.valueOf(id))) {
            return VarList.Not_Acceptable;
        } else {
            userRepository.deleteById(Integer.valueOf(id));
            return VarList.Created;
        }
    }

    public int generateNextUserId() {

        int latestId =  userRepository.findTopByOrderByUidDesc().orElse(new User()).getUID();
        return latestId + 1;
    }
}
