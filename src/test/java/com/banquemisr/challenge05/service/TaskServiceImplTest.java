package com.banquemisr.challenge05.service;

import com.banquemisr.challenge05.entity.Task;
import com.banquemisr.challenge05.entity.Users;
import com.banquemisr.challenge05.exception.TaskNotFoundException;
import com.banquemisr.challenge05.exception.UserNotFoundException;
import com.banquemisr.challenge05.mapper.TaskMapper;
import com.banquemisr.challenge05.model.dto.TaskRequestDto;
import com.banquemisr.challenge05.model.dto.TaskResponseDto;
import com.banquemisr.challenge05.model.enums.Priority;
import com.banquemisr.challenge05.model.enums.Status;
import com.banquemisr.challenge05.repository.TaskRepository;
import com.banquemisr.challenge05.repository.UserRepository;
import com.banquemisr.challenge05.service.serviceImpl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    private TaskRequestDto taskRequestDto;
    private Task task;
    private Users user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new Users();
        user.setId(1L);
        user.setUsername("testUser");

        taskRequestDto = new TaskRequestDto();
        taskRequestDto.setUserId(1L);
        taskRequestDto.setTitle("Test Task");
        taskRequestDto.setDescription("Task description");
        taskRequestDto.setStatus(Status.TODO);
        taskRequestDto.setPriority(Priority.HIGH);
        taskRequestDto.setDueDate(LocalDate.now());

        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Task description");
        task.setStatus(Status.TODO);
        task.setPriority(Priority.HIGH);
        task.setDueDate(LocalDate.now());
        task.setUsers(user);
    }

    @Test
    void testCreateTask() {

        when(userRepository.findById(taskRequestDto.getUserId())).thenReturn(Optional.of(user));
        when(taskMapper.toEntity(taskRequestDto)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toDto(task)).thenReturn(new TaskResponseDto(1L, "Test Task", "Task description", Priority.HIGH,Status.TODO, LocalDate.now(), user.getEmail()));

        TaskResponseDto response = taskServiceImpl.createTask(taskRequestDto);

        assertNotNull(response);
        assertEquals("Test Task", response.getTitle());
        verify(taskRepository).save(task);
    }

    @Test
    void testCreateTask_UserNotFound() {
        when(userRepository.findById(taskRequestDto.getUserId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> taskServiceImpl.createTask(taskRequestDto));
    }

    @Test
    void testDeleteTask() {

        when(taskRepository.existsById(1L)).thenReturn(true);

        taskServiceImpl.deleteTask(1L);

        verify(taskRepository).deleteById(1L);
    }

    @Test
    void testDeleteTask_NotFound() {

        when(taskRepository.existsById(1L)).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> taskServiceImpl.deleteTask(1L));
    }


}
