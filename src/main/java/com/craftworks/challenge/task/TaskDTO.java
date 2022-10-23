package com.craftworks.challenge.task;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private TaskPriority priority;
    private TaskStatus status;
    private LocalDateTime dueDate;

    public Long getPriority() {
        return this.priority.value;
    }

    public Long getStatus() {
        return this.status.value;
    }
}
