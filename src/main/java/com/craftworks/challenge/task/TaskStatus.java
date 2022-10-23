package com.craftworks.challenge.task;

public enum TaskStatus {

    OPEN(0L),
    COMPLETED(1L);

    public final Long value;

    TaskStatus(Long value) {
        this.value = value;
    }
}
