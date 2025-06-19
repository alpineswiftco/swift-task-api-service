package com.alpine.swift.task.api.controller;

import com.alpine.swift.task.api.exception.InvalidRequestException;
import com.alpine.swift.task.api.exception.TaskNotFoundException;
import com.alpine.swift.task.api.model.Task;
import com.alpine.swift.task.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class  TaskController<T> {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<?> getLists(@RequestParam String userId){
        List<Task> fetchedTask = taskService.getTasksByUser(userId);
        if(fetchedTask.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fetchedTask);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable(required = true) String id) throws InvalidRequestException, TaskNotFoundException {
        if(!StringUtils.hasLength(id)){
           throw new InvalidRequestException("Input query parameter is invalid");
        }
        return taskService.getTask(id).orElseThrow(() -> new TaskNotFoundException("Task not found for the given Id"));
    }


    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task){
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable String id, @RequestBody Task task) throws TaskNotFoundException {
        return new ResponseEntity<>(taskService.updateTask(id, task),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
