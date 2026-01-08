package com.macmillan.Auth_Service.controller;

import com.macmillan.Auth_Service.dto.RoleUpdateDTO;
import com.macmillan.Auth_Service.dto.UserDTO;
import com.macmillan.Auth_Service.mapper.UserMapper;
import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUsers")
    public List<User> getAllUsers(){
        return adminService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        User user = adminService.getUserById(id);

        if (user != null) {
            UserDTO userDTO = userMapper.mapUser(user);
            return ResponseEntity.ok(userDTO);
        }
        return new ResponseEntity<>("User Does Not Exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/user/{id}/role")
    public ResponseEntity<?> updateRole(@PathVariable int id,@RequestBody RoleUpdateDTO roleUpdateDTO){
        UserDTO userDTO = adminService.updateRole(id, roleUpdateDTO);

        if (userDTO == null)
            return new ResponseEntity<>("User Does Not Exist", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id){
        User user = adminService.getUserById(id);
        if (user != null) {
            adminService.deleteUserById(user.getUser_id());
            return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
    }
}
