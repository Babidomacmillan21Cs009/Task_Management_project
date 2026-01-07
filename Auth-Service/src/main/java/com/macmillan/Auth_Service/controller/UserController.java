package com.macmillan.Auth_Service.controller;

import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        userService.register(user);
        return new ResponseEntity<>("Registered successfully", HttpStatus.OK);
    }
}


















