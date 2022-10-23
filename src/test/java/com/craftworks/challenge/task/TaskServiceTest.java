package com.craftworks.challenge.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskServiceTest {

    @Autowired
    private TaskRepository taskRepository;

    private TaskService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TaskService(taskRepository);
    }

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    void getTasks() {
        Task firstTask = new Task(
                "First Task",
                "Do your challenge",
                TaskPriority.LOW,
                TaskStatus.OPEN,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Task secondTask = new Task(
                "Second Task",
                "Get Job",
                TaskPriority.LOW,
                TaskStatus.OPEN,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        taskRepository.saveAll(Arrays.asList(firstTask, secondTask));

        List<Task> tasks = underTest.getTasks();

        assertEquals(2, tasks.size());
    }

    @Test
    void getTask() {
        Task task = new Task(
                1L,
                "First Task",
                "Do your challenge",
                TaskPriority.LOW,
                TaskStatus.OPEN,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );

        taskRepository.save(task);

        Task actual = underTest.getTaskById(1L);
        assertEquals(1L, actual.getId());
    }

}
