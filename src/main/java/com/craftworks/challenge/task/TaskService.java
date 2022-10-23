package com.craftworks.challenge.task;

import com.craftworks.challenge.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Scheduled(fixedRate = 15000)
    public void scheduleNewTaskEvery15Seconds() {

        LOGGER.info("create new task by scheduler");

        Task task = new Task(
                "Title",
                "Description",
                TaskPriority.LOW,
                TaskStatus.OPEN,
                LocalDateTime.of(2023, 2, 2, 12, 0),
                LocalDateTime.now(),
                LocalDateTime.now());
        taskRepository.save(task);
    }

    public List<Task> getTasks() {
        LOGGER.info("return all tasks ordered by id");
        return taskRepository.findAllByOrderByIdAsc();
    }

    public Task getTaskById(Long taskId) {
        LOGGER.info("get task by id " + taskId);
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            return task.get();
        }

        LOGGER.warn("task with id {} not found", taskId);
        throw new NotFoundException("Task with id " + taskId + " not found");
    }

    public Task createTask(Task task) {
        LOGGER.info("create new task");
        return(taskRepository.save(task));
    }

    public Task updateTask(Task task) {
        LOGGER.info("update task with id {}", task.getId());
        Optional<Task> optionalTask = taskRepository.findById(task.getId());
        if (optionalTask.isPresent()) {
            Task presentTask = optionalTask.get();
            presentTask.setUpdatedAt(task.getUpdatedAt());
            presentTask.setDescription(task.getDescription());
            presentTask.setTitle(task.getTitle());
            presentTask.setPriority(task.getPriority());
            presentTask.setDueDate(task.getDueDate());
            if (task.getStatus().equals(TaskStatus.COMPLETED) && presentTask.getStatus().equals(TaskStatus.OPEN)) {
                presentTask.setResolvedAt(LocalDateTime.now());
            } else if (task.getStatus().equals(TaskStatus.OPEN) && presentTask.getStatus().equals(TaskStatus.COMPLETED)) {
                presentTask.setResolvedAt(null);
            }
            presentTask.setStatus(task.getStatus());
            return(taskRepository.save(presentTask));
        }
        LOGGER.warn("task with id {} not found", task.getId());
        throw new NotFoundException("Task with id " + task.getId() + " not found");
    }

    public void deleteTask(Long taskId) {
        LOGGER.info("delete task with id {}", taskId);
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(taskId);
        } else {
            LOGGER.warn("task with id {} not found", taskId);
            throw new NotFoundException("Task with id " + taskId + " not found");
        }
    }
}
