package com.craftworks.challenge.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class TaskCreationDTO {

    @NotBlank(message = "title must be not empty")
    private String title;

    private String description;

    // TODO: create validator for range
    @NotNull(message = "task priority must be not null")
    private TaskPriority priority;

    @Future(message = "due date must be in the future")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime dueDate;

    @JsonIgnore
    private final LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    private final LocalDateTime updatedAt = LocalDateTime.now();

    @JsonIgnore
    private TaskStatus status = TaskStatus.OPEN;

}
