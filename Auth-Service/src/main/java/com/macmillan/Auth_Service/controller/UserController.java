package com.macmillan.Auth_Service.controller;

import com.macmillan.Auth_Service.dto.UserDTO;
import com.macmillan.Auth_Service.dto.UserUpdateDTO;
import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.model.UserPrincipal;
import com.macmillan.Auth_Service.repo.UserRepo;
import com.macmillan.Auth_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/getProfile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDTO userDTO = userService.getUserByUsername(userPrincipal.getUsername());
        if (userDTO == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                        @RequestBody UserUpdateDTO userUpdateDTO){

        if (userPrincipal == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userService.updateUser(userPrincipal,userUpdateDTO);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal UserPrincipal userPrincipal){
        if (userPrincipal == null)
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        userService.deleteUser(userPrincipal.getUsername());
        return new ResponseEntity<>("Successfullty Seleted",HttpStatus.OK);
    }
}


















