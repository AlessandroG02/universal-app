package com.example.universalapp.controller;

import com.example.universalapp.dto.ManagerDTO;
import com.example.universalapp.service.ManagerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/managers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Manager", description = "CRUD Manager")
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping
    public List<ManagerDTO> getAll() {
        return managerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(managerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ManagerDTO> create(@RequestBody ManagerDTO dto) {
        return ResponseEntity.ok(managerService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagerDTO> update(
            @PathVariable Long id, @RequestBody ManagerDTO dto) {
        return ResponseEntity.ok(managerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        managerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
