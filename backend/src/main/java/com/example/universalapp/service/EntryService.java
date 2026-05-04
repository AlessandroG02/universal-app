package com.example.universalapp.service;

import com.example.universalapp.dto.EntryDTO;
import java.util.List;

public interface EntryService {
    List<EntryDTO> findAll();
    List<EntryDTO> findByMemberId(Long memberId);
    EntryDTO findById(Long id);
    EntryDTO save(EntryDTO dto);
    EntryDTO update(Long id, EntryDTO dto);
    void delete(Long id);
}
