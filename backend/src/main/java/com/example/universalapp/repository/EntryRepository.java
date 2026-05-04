package com.example.universalapp.repository;

import com.example.universalapp.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByMemberId(Long memberId);
    List<Entry> findByStatus(String status);
}
