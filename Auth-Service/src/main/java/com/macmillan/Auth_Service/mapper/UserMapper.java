package com.macmillan.Auth_Service.mapper;

import com.macmillan.Auth_Service.dto.UserDTO;
import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public UserDTO mapUser(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user.getUser_id());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setUpdatedDate(user.getUpdatedDate());
        return userDTO;
    }
}
