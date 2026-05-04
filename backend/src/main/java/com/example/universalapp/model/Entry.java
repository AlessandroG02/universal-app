package com.example.universalapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "entries")
@Data
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(length = 50)
    private String status = "PENDING";

    @Column(length = 255)
    private String note;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
