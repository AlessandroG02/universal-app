package com.example.universalapp.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EntryDTO {
    private Long id;
    private LocalDate date;
    private String status;
    private String note;
    private Long memberId;
    private String memberName;
}
