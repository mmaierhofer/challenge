package com.craftworks.challenge.task;

public enum TaskPriority {

    LOW(0L),
    MEDIUM(1L),
    HIGH(2L);

    public final Long value;

    TaskPriority(Long value) {
        this.value = value;
    }
}
