package com.macmillan.Auth_Service.controller;

import com.macmillan.Auth_Service.dto.UserDTO;
import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.model.UserPrincipal;
import com.macmillan.Auth_Service.repo.UserRepo;
import com.macmillan.Auth_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody User user){
        User exist_user = userService.getUserByEmail(user.getEmail());

        if (exist_user != null){
            return new ResponseEntity<>("Account already exists", HttpStatus.NOT_ACCEPTABLE);
        }
        userService.register(user);
        return new ResponseEntity<>("Registered SuccessFully",HttpStatus.ACCEPTED);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }

    @GetMapping("/user/getProfile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserPrincipal userPrincipal){
        System.out.println(userPrincipal.getUsername());
        if (userPrincipal == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userService.getUserByUsername(userPrincipal.getUsername());
        System.out.println(userPrincipal.getUsername());
        if (user != null){
            UserDTO userDTO = new UserDTO();
            userDTO.setUser_id(user.getUser_id());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setRole(user.getRole());
            userDTO.setCreatedDate(user.getCreatedDate());
            userDTO.setUpdatedDate(user.getUpdatedDate());
            return new ResponseEntity<>(userDTO,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


















