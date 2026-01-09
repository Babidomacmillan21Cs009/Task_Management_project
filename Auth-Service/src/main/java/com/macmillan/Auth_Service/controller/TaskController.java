package com.macmillan.Auth_Service.controller;


import com.macmillan.Auth_Service.dto.TaskDTO;
import com.macmillan.Auth_Service.dto.TaskResponseDTO;
import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.model.UserPrincipal;
import com.macmillan.Auth_Service.repo.TaskRepo;
import com.macmillan.Auth_Service.repo.UserRepo;
import com.macmillan.Auth_Service.service.TaskService;
import com.macmillan.Auth_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                        @RequestBody TaskDTO taskDTO){

         taskService.createTask(userPrincipal, taskDTO);
         return ResponseEntity.ok("Task Created");
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(@AuthenticationPrincipal
                                                                 UserPrincipal userPrincipal){

        User user = userService.findByUsername(userPrincipal.getUsername());
        int user_id = user.getUser_id();

        List<TaskResponseDTO> tasks = taskService.getAllTasks(user_id);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<?> getTaskById(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                       @PathVariable int taskId){

        User user = userService.findByUsername(userPrincipal.getUsername());
        int userId = user.getUser_id();

        TaskResponseDTO task = taskService.getTaskById(userId, taskId);

        if (task == null)
            return new ResponseEntity<>("No Task Found", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/task/{taskId}")
    public ResponseEntity<?> updateTask(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                        @PathVariable int taskId,
                                        @RequestBody TaskDTO taskDTO){

        User user = userService.findByUsername(userPrincipal.getUsername());
        int userId = user.getUser_id();

        TaskResponseDTO taskResponseDTO = taskService.updateTask(userId, taskId, taskDTO);
        if (taskResponseDTO == null)
            return new ResponseEntity<>("Task Not Found", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(taskResponseDTO);

    }

}
