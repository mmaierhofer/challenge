package com.craftworks.challenge.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class TaskUpdateDTO {

    @Id
    @NotNull(message = "id must be not null")
    private Long id;

    @NotBlank(message = "title must be not empty")
    private String title;

    private String description;

    // TODO: create validator for range
    @NotNull(message = "task priority must be not null")
    private TaskPriority priority;

    @Future(message = "due date must be in the future")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime dueDate;

    // TODO: create validator for range
    @NotNull(message = "task status must be not null")
    private TaskStatus status;

    @JsonIgnore
    private final LocalDateTime updatedAt = LocalDateTime.now();
}
