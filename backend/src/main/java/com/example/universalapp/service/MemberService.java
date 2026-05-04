package com.example.universalapp.service;

import com.example.universalapp.dto.MemberDTO;
import java.util.List;

public interface MemberService {
    List<MemberDTO> findAll();
    List<MemberDTO> findByManagerId(Long managerId);
    MemberDTO findById(Long id);
    MemberDTO save(MemberDTO dto);
    MemberDTO update(Long id, MemberDTO dto);
    void delete(Long id);
}
