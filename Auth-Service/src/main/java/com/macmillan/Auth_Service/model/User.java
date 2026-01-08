package com.macmillan.Auth_Service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String username;
    private String email;
    private String password;
    private String role;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyy")
    private LocalDate createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyy")
    private LocalDate updatedDate;
}
