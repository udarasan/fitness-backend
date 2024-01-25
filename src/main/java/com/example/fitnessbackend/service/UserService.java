package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.UserDTO;
import com.example.fitnessbackend.dto.WorkOutPlanDTO;
import com.example.fitnessbackend.entity.MealPlan;
import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.entity.User;
import com.example.fitnessbackend.entity.WorkOutPlan;
import com.example.fitnessbackend.repo.MealPlanRepository;
import com.example.fitnessbackend.repo.TrainerRepository;
import com.example.fitnessbackend.repo.UserRepository;
import com.example.fitnessbackend.repo.WorkOutPlanRepository;
import com.example.fitnessbackend.util.VarList;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private MealPlanRepository mealPlanRepository;
    @Autowired
    private WorkOutPlanRepository workOutPlanRepository;
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
        System.out.println(user.getPassword());
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
                    if(user.getMealPlan() != null){
                        userDTO.setMeal_plan_id(user.getMealPlan().getMID());
                    }
                    if(user.getWorkOutPlan() != null){
                        userDTO.setWorkout_id(user.getWorkOutPlan().getWID());
                    }
                    return userDTO;
                })
                .collect(Collectors.toList());
    }

    public UserDTO searchUser(String email) {
        if (userRepository.existsByEmail(email)) {
            User user = userRepository.findByEmail(email);

            if (user != null) {
                UserDTO userDTO = modelMapper.map(user, UserDTO.class);
                if (user.getTrainer() != null) {
                    userDTO.setTrainer_id(user.getTrainer().getTID());
                }

                if (user.getMealPlan() != null) {
                    userDTO.setMeal_plan_id(user.getMealPlan().getMID());
                }

                if (user.getWorkOutPlan() != null) {
                    userDTO.setWorkout_id(user.getWorkOutPlan().getWID());
                }

                return userDTO;
            } else {
                return null;
            }
        } else {
            return null;
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

    public int updateUser(UserDTO userDTO) {
        Optional<User> existingUserOptional = Optional.ofNullable(userRepository.findByEmail(userDTO.getEmail()));

        if (existingUserOptional.isPresent()) {


            User existingUser = existingUserOptional.get();

            modelMapper.map(userDTO, existingUser);

            int trainerId = userDTO.getTrainer_id();
            int workOutId = userDTO.getWorkout_id();
            int mealPlanId = userDTO.getMeal_plan_id();
            Trainer trainer = trainerRepository.findById(trainerId).orElse(null);
            WorkOutPlan workOutPlan = workOutPlanRepository.findById(workOutId).orElse(null);
            MealPlan mealPlan = mealPlanRepository.findById(mealPlanId).orElse(null);


            existingUser.setTrainer(trainer);
            existingUser.setWorkOutPlan(workOutPlan);
            existingUser.setMealPlan(mealPlan);


            userRepository.save(existingUser);

            return VarList.Created;
        } else {
            // User with the given email does not exist
            return VarList.Not_Acceptable;
        }
    }

    public int getNumberOfMembers() {
        int count = (int) userRepository.count();
        return count;
    }

    public List<UserDTO> searchUsersByName(String partialName) {
        List<User> plans = userRepository.findByNameStartingWith(partialName);

        List<UserDTO> userDTOS = plans.stream()
                .map(name -> modelMapper.map(name, UserDTO.class))
                .collect(Collectors.toList());

        return userDTOS;
    }

//    public String generateNextUserId() {
////        Optional<User> latestUser = userRepository.findTopByOrderByUIDDesc();
////        return latestUser.map(User::getUID).orElse("0");
//    }
}
