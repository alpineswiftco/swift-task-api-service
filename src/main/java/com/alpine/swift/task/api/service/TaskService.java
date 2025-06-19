package com.alpine.swift.task.api.service;

import com.alpine.swift.task.api.exception.TaskNotFoundException;
import com.alpine.swift.task.api.model.Task;
import com.alpine.swift.task.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public Task createTask(Task task){
        task.setStatus("To Do");
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(task.getCreatedAt());
        return taskRepository.save(task);
    }

    public List<Task> getTasksByUser(String userId){
        return taskRepository.findByUserId(userId);
    }

    public Optional<Task> getTask(String guid){
        return taskRepository.findById(guid);
    }

    public void deleteTask(String guid){
        taskRepository.deleteById(guid);
    }
    public Task updateTask(String guid, Task updated) throws TaskNotFoundException {
        Task task = taskRepository.findById(guid).orElseThrow(() -> new TaskNotFoundException("Task Not Found"));
        task.setTitle(updated.getTitle());
        task.setDescription(updated.getDescription());
        task.setDeadline(updated.getDeadline());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

}
