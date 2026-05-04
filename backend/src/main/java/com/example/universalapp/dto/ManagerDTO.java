package com.example.universalapp.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ManagerDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private int memberCount;
}
