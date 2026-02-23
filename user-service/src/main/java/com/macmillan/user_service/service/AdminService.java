package com.macmillan.Auth_Service.service;


import com.macmillan.Auth_Service.dto.RoleUpdateDTO;
import com.macmillan.Auth_Service.dto.UserDTO;
import com.macmillan.Auth_Service.mapper.UserMapper;
import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(int id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null)
            return null;
        return user;
    }


    public UserDTO updateRole(int id, RoleUpdateDTO roleUpdateDTO) {
        User user = userRepo.findById(id).orElse(null);

        if (user == null)
            return null;
        user.setRole(roleUpdateDTO.getRole());
        userRepo.save(user);
        return userMapper.mapUser(user);
    }

    public void deleteUserById(int id) {
        userRepo.deleteById(id);
    }
}
