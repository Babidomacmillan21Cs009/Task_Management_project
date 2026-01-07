package com.macmillan.Auth_Service.service;

import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUser() {
        return userRepo.findAll();
    }


    public void register(User user) {
        userRepo.save(user);
    }
}















