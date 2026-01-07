package com.macmillan.Auth_Service.service;

import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    public List<User> getAllUser() {
        return userRepo.findAll();
    }


    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public String verify(User user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername(),user.getRole());
        }
        return "fail";
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}















