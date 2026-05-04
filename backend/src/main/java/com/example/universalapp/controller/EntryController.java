package com.example.universalapp.controller;

import com.example.universalapp.dto.EntryDTO;
import com.example.universalapp.service.EntryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entries")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Entry", description = "CRUD Entry")
public class EntryController {

    private final EntryService entryService;

    @GetMapping
    public List<EntryDTO> getAll() {
        return entryService.findAll();
    }

    @GetMapping("/by-member/{memberId}")
    public List<EntryDTO> getByMember(@PathVariable Long memberId) {
        return entryService.findByMemberId(memberId);
    }

    @PostMapping
    public ResponseEntity<EntryDTO> create(@RequestBody EntryDTO dto) {
        return ResponseEntity.ok(entryService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntryDTO> update(
            @PathVariable Long id, @RequestBody EntryDTO dto) {
        return ResponseEntity.ok(entryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
