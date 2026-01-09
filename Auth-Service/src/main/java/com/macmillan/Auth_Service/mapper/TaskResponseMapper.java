package com.macmillan.Auth_Service.mapper;

import com.macmillan.Auth_Service.dto.TaskResponseDTO;
import com.macmillan.Auth_Service.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskResponseMapper {

    public TaskResponseDTO mapper(Task task){
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setTask_id(task.getTask_id());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setStatus(task.getStatus());
        taskResponseDTO.setDeadline(task.getDeadline());
        return taskResponseDTO;
    }
}
