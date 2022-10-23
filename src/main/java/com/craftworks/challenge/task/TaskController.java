package com.craftworks.challenge.task;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(path = "api/v1/task")
@RestController
public class TaskController {
    private final TaskService taskService;

    private final ModelMapper modelMapper;

    @Autowired
    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "all")
    ResponseEntity<List<TaskDTO>> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return new ResponseEntity<>(tasks.stream().map(task -> modelMapper.map(task, TaskDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(path = "{taskId}")
    ResponseEntity<TaskDTO> getTask(@PathVariable("taskId") Long id) {
        Task task = taskService.getTaskById(id);
        return new ResponseEntity<>(modelMapper.map(task, TaskDTO.class), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskCreationDTO taskCreationDTO) {
        Task task = taskService.createTask(modelMapper.map(taskCreationDTO, Task.class));
        return new ResponseEntity<>(modelMapper.map(task, TaskDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<TaskDTO> updateTask(@RequestBody @Valid TaskUpdateDTO taskUpdateDTO) {
        Task task = taskService.updateTask(modelMapper.map(taskUpdateDTO, Task.class));
        return new ResponseEntity<>(modelMapper.map(task, TaskDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(path = "{taskId}")
    ResponseEntity<String> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>("Task with id " + taskId + " deleted", HttpStatus.OK);
    }






}
