package com.example.universalapp.service.impl;

import com.example.universalapp.dto.MemberDTO;
import com.example.universalapp.model.Manager;
import com.example.universalapp.model.Member;
import com.example.universalapp.repository.ManagerRepository;
import com.example.universalapp.repository.MemberRepository;
import com.example.universalapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ManagerRepository managerRepository;

    @Override
    public List<MemberDTO> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> findByManagerId(Long managerId) {
        return memberRepository.findByManagerId(managerId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO findById(Long id) {
        Member m = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found: " + id));
        return toDTO(m);
    }

    @Override
    public MemberDTO save(MemberDTO dto) {
        Member m = toEntity(dto);
        return toDTO(memberRepository.save(m));
    }

    @Override
    public MemberDTO update(Long id, MemberDTO dto) {
        Member m = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found: " + id));
        m.setName(dto.getName());
        m.setEmail(dto.getEmail());
        if (dto.getManagerId() != null) {
            Manager mgr = managerRepository.findById(dto.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found: " + dto.getManagerId()));
            m.setManager(mgr);
        }
        return toDTO(memberRepository.save(m));
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    private MemberDTO toDTO(Member m) {
        MemberDTO dto = new MemberDTO();
        dto.setId(m.getId());
        dto.setName(m.getName());
        dto.setEmail(m.getEmail());
        if (m.getManager() != null) {
            dto.setManagerId(m.getManager().getId());
            dto.setManagerName(m.getManager().getName());
        }
        dto.setEntryCount(m.getEntries() != null ? m.getEntries().size() : 0);
        return dto;
    }

    private Member toEntity(MemberDTO dto) {
        Member m = new Member();
        m.setName(dto.getName());
        m.setEmail(dto.getEmail());
        if (dto.getManagerId() != null) {
            Manager mgr = managerRepository.findById(dto.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found: " + dto.getManagerId()));
            m.setManager(mgr);
        }
        return m;
    }
}
