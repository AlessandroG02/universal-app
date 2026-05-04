package com.example.universalapp.service.impl;

import com.example.universalapp.dto.EntryDTO;
import com.example.universalapp.model.Entry;
import com.example.universalapp.model.Member;
import com.example.universalapp.repository.EntryRepository;
import com.example.universalapp.repository.MemberRepository;
import com.example.universalapp.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<EntryDTO> findAll() {
        return entryRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntryDTO> findByMemberId(Long memberId) {
        return entryRepository.findByMemberId(memberId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EntryDTO findById(Long id) {
        Entry e = entryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found: " + id));
        return toDTO(e);
    }

    @Override
    public EntryDTO save(EntryDTO dto) {
        Entry e = toEntity(dto);
        return toDTO(entryRepository.save(e));
    }

    @Override
    public EntryDTO update(Long id, EntryDTO dto) {
        Entry e = entryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found: " + id));
        e.setDate(dto.getDate());
        e.setStatus(dto.getStatus());
        e.setNote(dto.getNote());
        if (dto.getMemberId() != null) {
            Member m = memberRepository.findById(dto.getMemberId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found: " + dto.getMemberId()));
            e.setMember(m);
        }
        return toDTO(entryRepository.save(e));
    }

    @Override
    public void delete(Long id) {
        if (!entryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found: " + id);
        }
        entryRepository.deleteById(id);
    }

    private EntryDTO toDTO(Entry e) {
        EntryDTO dto = new EntryDTO();
        dto.setId(e.getId());
        dto.setDate(e.getDate());
        dto.setStatus(e.getStatus());
        dto.setNote(e.getNote());
        if (e.getMember() != null) {
            dto.setMemberId(e.getMember().getId());
            dto.setMemberName(e.getMember().getName());
        }
        return dto;
    }

    private Entry toEntity(EntryDTO dto) {
        Entry e = new Entry();
        e.setDate(dto.getDate());
        e.setStatus(dto.getStatus() != null ? dto.getStatus() : "PENDING");
        e.setNote(dto.getNote());
        if (dto.getMemberId() != null) {
            Member m = memberRepository.findById(dto.getMemberId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found: " + dto.getMemberId()));
            e.setMember(m);
        }
        return e;
    }
}
