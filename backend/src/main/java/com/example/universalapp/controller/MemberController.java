package com.example.universalapp.controller;

import com.example.universalapp.dto.MemberDTO;
import com.example.universalapp.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Member", description = "CRUD Member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberDTO> getAll() {
        return memberService.findAll();
    }

    @GetMapping("/by-manager/{managerId}")
    public List<MemberDTO> getByManager(@PathVariable Long managerId) {
        return memberService.findByManagerId(managerId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MemberDTO> create(@RequestBody MemberDTO dto) {
        return ResponseEntity.ok(memberService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> update(
            @PathVariable Long id, @RequestBody MemberDTO dto) {
        return ResponseEntity.ok(memberService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
