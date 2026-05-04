package com.example.universalapp.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private Long id;
    private String name;
    private String email;
    private Long managerId;
    private String managerName;
    private int entryCount;
}
