package com.example.universalapp.repository;

import com.example.universalapp.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByManagerId(Long managerId);
    boolean existsByEmail(String email);
}
