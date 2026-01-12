package com.macmillan.Auth_Service.service;


import com.macmillan.Auth_Service.dto.TaskDTO;
import com.macmillan.Auth_Service.dto.TaskResponseDTO;
import com.macmillan.Auth_Service.dto.TaskUpdateStatusDto;
import com.macmillan.Auth_Service.mapper.TaskResponseMapper;
import com.macmillan.Auth_Service.model.Task;
import com.macmillan.Auth_Service.model.User;
import com.macmillan.Auth_Service.model.UserPrincipal;
import com.macmillan.Auth_Service.repo.TaskRepo;
import com.macmillan.Auth_Service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private TaskResponseMapper taskResponseMapper;

    public void createTask(UserPrincipal userPrincipal, TaskDTO taskDTO) {

        User user = userRepo.findByUsername(userPrincipal.getUsername());
        int user_id = user.getUser_id();

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDeadline(taskDTO.getDeadline());
        task.setCreatedBy(user_id);
        task.setStatus(taskDTO.getStatus());
        task.setCreatedDate(LocalDate.now());
        task.setUpdatedDate(LocalDate.now());
        taskRepo.save(task);
    }

    public List<TaskResponseDTO> getAllTasksOfUser(int user_id) {

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

    public TaskResponseDTO getTaskById(int userId, int taskId) {
        Task task = taskRepo.findById(taskId).orElse(null);

        if (task != null && (userId == task.getCreatedBy() || userId == task.getAssignedTo())){
            return taskResponseMapper.mapper(task);
        }
        return null;
    }

    public TaskResponseDTO updateTask(int userId, int taskId, TaskDTO taskDTO) {
        Task task = taskRepo.findById(taskId).orElse(null);

        if (task != null && (userId == task.getCreatedBy() || userId == task.getAssignedTo())){
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setStatus(taskDTO.getStatus());
            task.setDeadline(taskDTO.getDeadline());
            taskRepo.save(task);
            return taskResponseMapper.mapper(task);
        }
        return null;
    }

    public boolean deleteTask(int userId, int taskId) {
        Optional<Task>  optionalTask = taskRepo.findById(taskId);
        if (optionalTask.isEmpty())
            return false;

        Task task = optionalTask.get();
        if (task.getCreatedBy() != userId &&
                task.getAssignedTo() != userId){
            return false;
        }
        taskRepo.delete(task);
        return true;
    }

    public int assignTask(int taskId, int userId) {
        Optional<Task> optionalTask = taskRepo.findById(taskId);
        if (optionalTask.isEmpty())
            return -1;

        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isEmpty())
            return -2;

        Task task = optionalTask.get();
        task.setAssignedTo(userId);
        task.setUpdatedDate(LocalDate.now());
        taskRepo.save(task);
        return 1;
    }

    public List<Task> getAllTask() {
        return taskRepo.findAll();
    }

    public List<TaskResponseDTO> getAssignedTasks(int userId) {
        List<Task> tasks = taskRepo.findByAssignedTo(userId);

        return tasks.stream()
                .map(task -> new TaskResponseDTO(
                        task.getTask_id(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus(),
                        task.getDeadline()
                ))
                .collect(Collectors.toList());
    }

    public boolean updateStatus(int userId, int taskId, TaskUpdateStatusDto taskUpdateStatusDto) {
        Optional<Task> optionalTask = taskRepo.findById(taskId);
        if (optionalTask.isEmpty())
            return false;
        Task task = optionalTask.get();
        if (task.getCreatedBy() != userId && task.getAssignedTo() != userId)
            return false;

        task.setStatus(taskUpdateStatusDto.getStatus());
        task.setUpdatedDate(LocalDate.now());
        taskRepo.save(task);
        return true;
    }
}
