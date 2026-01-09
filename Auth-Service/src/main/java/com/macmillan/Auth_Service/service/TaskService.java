package com.macmillan.Auth_Service.service;


import com.macmillan.Auth_Service.dto.TaskDTO;
import com.macmillan.Auth_Service.dto.TaskResponseDTO;
import com.macmillan.Auth_Service.model.Task;
import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.model.UserPrincipal;
import com.macmillan.Auth_Service.repo.TaskRepo;
import com.macmillan.Auth_Service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    public void createTask(UserPrincipal userPrincipal, TaskDTO taskDTO) {

        User user = userRepo.findByUsername(userPrincipal.getUsername());
        int user_id = user.getUser_id();

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDeadline(taskDTO.getDeadline());
        task.setCreatedBy(user_id);
        task.setStatus(taskDTO.getStatus());
        taskRepo.save(task);
    }

    public List<TaskResponseDTO> getAllTasks(int user_id) {

        List<Task> tasks = taskRepo.findByCreatedBy(user_id);

       return tasks.stream()
               .map(task -> new TaskResponseDTO(
                       task.getTask_id(),
                       task.getTitle(),
                       task.getDescription(),
                       task.getStatus(),
                       task.getDeadline()
               )).collect(Collectors.toList());
    }
}
