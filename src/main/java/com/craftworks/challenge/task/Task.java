package com.craftworks.challenge.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    private String title;
    private String description;
    @NotNull
    private TaskPriority priority;
    @NotNull
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotNull
    private LocalDateTime dueDate;
    private LocalDateTime resolvedAt;

    public Task(String title, String description, TaskPriority priority, TaskStatus status, LocalDateTime dueDate, LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
