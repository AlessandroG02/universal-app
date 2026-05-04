package com.example.universalapp.service;

import com.example.universalapp.dto.ManagerDTO;
import java.util.List;

public interface ManagerService {
    List<ManagerDTO> findAll();
    ManagerDTO findById(Long id);
    ManagerDTO save(ManagerDTO dto);
    ManagerDTO update(Long id, ManagerDTO dto);
    void delete(Long id);
}
