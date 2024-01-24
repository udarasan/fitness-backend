package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.TrainerDTO;
import com.example.fitnessbackend.dto.UserDTO;
import com.example.fitnessbackend.entity.MealPlan;
import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.entity.User;
import com.example.fitnessbackend.entity.WorkOutPlan;
import com.example.fitnessbackend.repo.TrainerRepository;
import com.example.fitnessbackend.repo.UserRepository;
import com.example.fitnessbackend.util.VarList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @TimeStamp 2024-01-09 07:37
 * @ProjectDetails fitness-backend
 */
@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public boolean isValidTrainerCredentials(String username, String password) {
        Trainer trainer = trainerRepository.findByEmailAndPassword(username, password);
        return trainer != null;
    }

    public List<TrainerDTO> getAllTrainers() {
//        List<Trainer> users=trainerRepository.findAll();
//
//        return modelMapper.map(users, new TypeToken<ArrayList<TrainerDTO>>() {
//        }.getType());

        List<Trainer> trainers = trainerRepository.findAll();

        // Configure modelMapper to handle the mapping of trainer_id in UserDTO
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        Type listType = new TypeToken<ArrayList<TrainerDTO>>() {}.getType();
        return trainers.stream()
                .map(trainer -> {
                    TrainerDTO trainerDTO = modelMapper.map(trainer, TrainerDTO.class);
                    List<UserDTO> userDTOs = modelMapper.map(trainer.getUsers(), new TypeToken<ArrayList<UserDTO>>() {}.getType());

                    // Set the trainer_id in each UserDTO
                    userDTOs.forEach(userDTO -> userDTO.setTrainer_id((trainer.getTID())));

                    trainerDTO.setUsers(userDTOs);
                    return trainerDTO;
                })
                .collect(Collectors.toList());

    }

    public int saveTrainer(TrainerDTO trainerDTO) {
        if (trainerRepository.existsByEmail(trainerDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            trainerRepository.save(modelMapper.map(trainerDTO, Trainer.class));
            return VarList.Created;
        }
    }



    public int updateTrainer(TrainerDTO trainerDTO) {
        if (!trainerRepository.existsByEmail(trainerDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            trainerRepository.save(modelMapper.map(trainerDTO, Trainer.class));
            return VarList.Created;
        }
    }

    public int deleteTrainer(String id) {
        if (!trainerRepository.existsById(Integer.valueOf(id))) {
            return VarList.Not_Acceptable;
        } else {
            trainerRepository.deleteById(Integer.valueOf(id));
            return VarList.Created;
        }
    }


    public TrainerDTO searchTrainer(String email) {

        if (trainerRepository.existsByEmail(email)) {
            Trainer trainer=trainerRepository.findByEmail(email);
            return modelMapper.map(trainer,TrainerDTO.class);
        } else {
            return null;
        }
//        System.out.println("ddd");
//        if (trainerRepository.existsByEmail(email)) {
//            Trainer trainer = trainerRepository.findByEmail(email);
//
//            // Configure modelMapper to handle the mapping of trainer_id in UserDTO
//            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//            TrainerDTO trainerDTO = modelMapper.map(trainer, TrainerDTO.class);
//            List<UserDTO> userDTOs = modelMapper.map(trainer.getUsers(), new TypeToken<ArrayList<UserDTO>>() {}.getType());
//
//            // Assuming each User has a reference to a MealPlan
//            MealPlan mealPlan; // Initialize mealPlan to null
//            WorkOutPlan workOutPlan;
//            // Find the first user with a non-null MealPlan reference
//            User userWithMealPlan = trainer.getUsers().stream()
//                    .filter(u -> u.getMealPlan() != null)
//
//                    .findFirst()
//                    .orElse(null);
//            User userWithWorkOutPlan = trainer.getUsers().stream()
//                    .filter(u -> u.getWorkOutPlan() != null)
//                    .findFirst()
//                    .orElse(null);
//
//            if (userWithWorkOutPlan != null) {
//                workOutPlan = userWithWorkOutPlan.getWorkOutPlan();
//            } else {
//                workOutPlan = null;
//            }
//
//            if (userWithMealPlan != null) {
//                mealPlan = userWithMealPlan.getMealPlan();
//            } else {
//                mealPlan = null;
//            }
//
//            // Set the trainer_id and meal_plan_id in each UserDTO
//            userDTOs.forEach(userDTO -> {
//                userDTO.setTrainer_id(trainer.getTID());
//
//                userDTO.setMeal_plan_id(mealPlan != null ? mealPlan.getMID() : null);
//
//                userDTO.setWorkout_id(workOutPlan != null ? workOutPlan.getWID() : null);
//            });
//
//            trainerDTO.setUsers(userDTOs);
//            return trainerDTO;
//        } else {
//            return null; // Trainer with the specified email not found
//        }
    }

    public List<UserDTO> searchClentWithTrainer(int id) {
        List<User> users = userRepository.findAllByClient(id);

        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {

            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTO.setTrainer_id(user.getTrainer().getTID());
            if (user.getMealPlan() != null && user.getMealPlan().getMID()!=0){
                userDTO.setMeal_plan_id(user.getMealPlan().getMID());
            }


            userDTO.setTrainer_id(user.getTrainer().getTID());
            if (user.getWorkOutPlan() != null && user.getWorkOutPlan().getWID() != 0) {
                userDTO.setWorkout_id(user.getWorkOutPlan().getWID());
            }
            userDTOs.add(userDTO);
        }

        return userDTOs;

    }

    public int getNumberOfTrainers() {
        int count = (int) trainerRepository.count();
        return count;
    }

    public List<TrainerDTO> searchTrainerByName(String partialName) {
        List<Trainer> plans = trainerRepository.findByNameStartingWith(partialName);

        List<TrainerDTO> trainerDTOS = plans.stream()
                .map(name -> modelMapper.map(name, TrainerDTO.class))
                .collect(Collectors.toList());

        return trainerDTOS;
    }
}
