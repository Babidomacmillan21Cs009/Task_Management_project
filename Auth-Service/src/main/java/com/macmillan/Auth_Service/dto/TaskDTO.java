package com.macmillan.Auth_Service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private LocalDate deadline;
    private String status;
}
