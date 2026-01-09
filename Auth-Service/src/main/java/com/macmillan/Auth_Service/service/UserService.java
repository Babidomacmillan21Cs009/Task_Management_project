package com.macmillan.Auth_Service.service;

import com.macmillan.Auth_Service.dto.UserDTO;
import com.macmillan.Auth_Service.dto.UserUpdateDTO;
import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.model.UserPrincipal;
import com.macmillan.Auth_Service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public UserDTO getUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null)
            return null;

        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user.getUser_id());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setUpdatedDate(user.getUpdatedDate());
        return userDTO;
    }

    public User updateUser(UserPrincipal userPrincipal, UserUpdateDTO userUpdateDTO) {
        User exist_user = userRepo.findByUsername(userPrincipal.getUsername());
        if (exist_user == null){
            return null;
        }
        exist_user.setUsername(userUpdateDTO.getUsername());
        exist_user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        exist_user.setUpdatedDate(LocalDate.now());
        userRepo.save(exist_user);
        return exist_user;
    }

    public void deleteUser(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null)
            return;
        userRepo.deleteById(user.getUser_id());
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}















