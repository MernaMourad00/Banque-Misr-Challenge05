package com.banquemisr.challenge05.service;

import com.banquemisr.challenge05.model.dto.TaskRequestDto;
import com.banquemisr.challenge05.model.dto.TaskResponseDto;
import com.banquemisr.challenge05.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface TaskService {

    TaskResponseDto createTask(TaskRequestDto taskRequestDto);
    TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto);
    void deleteTask(Long id);
    Page<TaskResponseDto> getAllTasks(int pageNo, int pageSize);
    Page<TaskResponseDto> getTasksByUser(Long userId, int pageNo, int pageSize);
    Page<TaskResponseDto> getTasksByTitle(String title, Long userId, int pageNo, int pageSize);
    Page<TaskResponseDto> getTasksByStatus(Status status, Long userId, int pageNo, int pageSize);
    Page<TaskResponseDto> getTasksByDueDate(LocalDate dueDate, Long userId, int pageNo, int pageSize);
    Page<TaskResponseDto> getTasksByDescription(String description, Long userId, int pageNo, int pageSize);
    TaskResponseDto markTaskAsDone(Long id);
}
