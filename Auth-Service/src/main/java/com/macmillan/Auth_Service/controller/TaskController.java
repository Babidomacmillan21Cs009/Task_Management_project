package com.macmillan.Auth_Service.controller;


import com.macmillan.Auth_Service.dto.TaskDTO;
import com.macmillan.Auth_Service.dto.TaskResponseDTO;
import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.model.UserPrincipal;
import com.macmillan.Auth_Service.repo.UserRepo;
import com.macmillan.Auth_Service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                        @RequestBody TaskDTO taskDTO){

         taskService.createTask(userPrincipal, taskDTO);
         return ResponseEntity.ok("Task Created");
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(@AuthenticationPrincipal
                                                                 UserPrincipal userPrincipal){

        User user = userRepo.getUserByUsername(userPrincipal.getUsername());
        int user_id = user.getUser_id();

        List<TaskResponseDTO> tasks = taskService.getAllTasks(user_id);
        return ResponseEntity.ok(tasks);
    }

}
