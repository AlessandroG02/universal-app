package com.example.universalapp.service.impl;

import com.example.universalapp.dto.ManagerDTO;
import com.example.universalapp.model.Manager;
import com.example.universalapp.repository.ManagerRepository;
import com.example.universalapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    @Override
    public List<ManagerDTO> findAll() {
        return managerRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ManagerDTO findById(Long id) {
        Manager m = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found: " + id));
        return toDTO(m);
    }

    @Override
    public ManagerDTO save(ManagerDTO dto) {
        Manager m = toEntity(dto);
        return toDTO(managerRepository.save(m));
    }

    @Override
    public ManagerDTO update(Long id, ManagerDTO dto) {
        Manager m = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found: " + id));
        m.setName(dto.getName());
        m.setEmail(dto.getEmail());
        m.setPhone(dto.getPhone());
        return toDTO(managerRepository.save(m));
    }

    @Override
    public void delete(Long id) {
        managerRepository.deleteById(id);
    }

    private ManagerDTO toDTO(Manager m) {
        ManagerDTO dto = new ManagerDTO();
        dto.setId(m.getId());
        dto.setName(m.getName());
        dto.setEmail(m.getEmail());
        dto.setPhone(m.getPhone());
        dto.setCreatedAt(m.getCreatedAt());
        dto.setMemberCount(m.getMembers() != null ? m.getMembers().size() : 0);
        return dto;
    }

    private Manager toEntity(ManagerDTO dto) {
        Manager m = new Manager();
        m.setName(dto.getName());
        m.setEmail(dto.getEmail());
        m.setPhone(dto.getPhone());
        return m;
    }
}
