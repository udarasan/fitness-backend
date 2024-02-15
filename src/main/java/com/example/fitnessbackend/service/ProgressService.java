package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.ProgressDTO;
import com.example.fitnessbackend.dto.TrainerDTO;
import com.example.fitnessbackend.dto.UserDTO;
import com.example.fitnessbackend.entity.Chat;
import com.example.fitnessbackend.entity.Progress;
import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.entity.User;
import com.example.fitnessbackend.repo.ProgressRepository;
import com.example.fitnessbackend.repo.UserRepository;
import com.example.fitnessbackend.util.VarList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;
    public int saveProgress(ProgressDTO progressDTO) {
        if (progressRepository.existsById(progressDTO.getPid())) {
            return VarList.Not_Acceptable;
        } else {
            User user = userRepository.findById(progressDTO.getUserId()).orElse(null);


            Progress progress = modelMapper.map(progressDTO, Progress.class);

            progress.setUser(user);


            progressRepository.save(progress);
            return VarList.Created;
        }
    }

    public List<ProgressDTO> getAllProgress(int id) {
//        String hql = "FROM Progress p WHERE p.user.id = :userId";
//        List<Progress> progresses = entityManager.createQuery(hql, Progress.class)
//                .setParameter("userId", id)
//                .getResultList();
        List<Progress> progresses = progressRepository.findByProgress(id);

        List<ProgressDTO> progressDTOList = new ArrayList<>();

        for (Progress progress : progresses) {
            ProgressDTO progressDTO = modelMapper.map(progress, ProgressDTO.class);
            // Additional mapping or processing if needed
            progressDTOList.add(progressDTO);
        }

        return progressDTOList;
    }

    public int updateProgress(ProgressDTO progressDTO) {

        if (!progressRepository.existsById(progressDTO.getPid())) {
            return VarList.Not_Acceptable;
        } else {
            User user = userRepository.findById(progressDTO.getUserId()).orElse(null);


            Progress progress = modelMapper.map(progressDTO, Progress.class);

            progress.setUser(user);


            progressRepository.save(progress);
            return VarList.Created;
        }
    }

    public int deleteProgress(String id) {
        if (!progressRepository.existsById(Integer.valueOf(id))) {
            return VarList.Not_Acceptable;
        } else {
            progressRepository.deleteById(Integer.valueOf(id));
            return VarList.Created;
        }

    }

 /*   public List<ProgressDTO> searchProgressByMonth(String month) {

        List<Progress> progresses = progressRepository.findByMonth(month);

        List<ProgressDTO> equipmentDTOS = new ArrayList<>();

        for (Progress progress : progresses) {
            ProgressDTO progressDTO = modelMapper.map(progress, ProgressDTO.class);
            equipmentDTOS.add(progressDTO);
        }
        return equipmentDTOS;
    }*/
}
