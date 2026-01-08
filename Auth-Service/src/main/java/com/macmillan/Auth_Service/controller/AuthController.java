package com.macmillan.Auth_Service.controller;

import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.service.AuthService;
import com.macmillan.Auth_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        User exist_user = authService.getUserByEmail(user.getEmail());

        if (exist_user != null){
            return new ResponseEntity<>("Account already exists", HttpStatus.NOT_ACCEPTABLE);
        }
        authService.register(user);
        return new ResponseEntity<>("Registered SuccessFully",HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return authService.verify(user);
    }
}
