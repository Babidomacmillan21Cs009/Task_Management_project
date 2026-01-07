package com.macmillan.Auth_Service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Data
@RequiredArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String userName;
    private String email;
    private String password;
    private String role;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyy")
    private LocalDate createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyy")
    private LocalDate updatedDate;
}
