package com.example.fitnessbackend.service;

import com.example.fitnessbackend.dto.EquipmentDTO;
import com.example.fitnessbackend.dto.TrainerDTO;
import com.example.fitnessbackend.entity.Equipment;
import com.example.fitnessbackend.entity.Trainer;
import com.example.fitnessbackend.repo.EquipmentRepository;
import com.example.fitnessbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public int saveEquipment(EquipmentDTO equipmentDTO) {
        if (equipmentRepository.existsById(equipmentDTO.getEid())) {
            return VarList.Not_Acceptable;
        } else {
            equipmentRepository.save(modelMapper.map(equipmentDTO, Equipment.class));
            return VarList.Created;
        }

    }
    public List<EquipmentDTO> getAllEquipment() {

        List<Equipment> equipment=equipmentRepository.findAll();

        return modelMapper.map(equipment, new TypeToken<ArrayList<EquipmentDTO>>() {
        }.getType());
    }

    public int updateEquipment(EquipmentDTO equipmentDTO) {
        if (!equipmentRepository.existsById(equipmentDTO.getEid())) {
            return VarList.Not_Acceptable;
        } else {
            equipmentRepository.save(modelMapper.map(equipmentDTO, Equipment.class));
            return VarList.Created;
        }
    }

    public int deleteEquipment(String id) {

        if (!equipmentRepository.existsById(Integer.valueOf(id))) {
            return VarList.Not_Acceptable;
        } else {
            equipmentRepository.deleteById(Integer.valueOf(id));
            return VarList.Created;
        }
    }

    public List<EquipmentDTO> searchEquipmentByName(String partialName) {
        List<Equipment> plans = equipmentRepository.findByequipmentNameStartingWith(partialName);

        List<EquipmentDTO> equipmentDTOS = plans.stream()
                .map(equipmentName -> modelMapper.map(equipmentName, EquipmentDTO.class))
                .collect(Collectors.toList());

        return equipmentDTOS;
    }

    public int getNumberOfEquipments() {
        int count = (int) equipmentRepository.count();
        return count;
    }
}
