package com.banquemisr.challenge05.service.serviceImpl;

import com.banquemisr.challenge05.entity.Task;
import com.banquemisr.challenge05.entity.Users;
import com.banquemisr.challenge05.exception.TaskNotFoundException;
import com.banquemisr.challenge05.exception.UserNotFoundException;
import com.banquemisr.challenge05.mapper.TaskMapper;
import com.banquemisr.challenge05.model.dto.TaskRequestDto;
import com.banquemisr.challenge05.model.dto.TaskResponseDto;
import com.banquemisr.challenge05.model.enums.Status;
import com.banquemisr.challenge05.repository.TaskRepository;
import com.banquemisr.challenge05.repository.UserRepository;
import com.banquemisr.challenge05.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;

    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {

        Users users = userRepository.findById(taskRequestDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + taskRequestDto.getUserId()));

        Task task = taskMapper.toEntity(taskRequestDto);
        task.setUsers(users);

        return taskMapper.toDto(taskRepository.save(task));
    }


    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));

        Users users = userRepository.findById(taskRequestDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + taskRequestDto.getUserId()));

        existingTask.setTitle(taskRequestDto.getTitle());
        existingTask.setDescription(taskRequestDto.getDescription());
        existingTask.setStatus(taskRequestDto.getStatus());
        existingTask.setPriority(taskRequestDto.getPriority());
        existingTask.setDueDate(taskRequestDto.getDueDate());
        existingTask.setUsers(users);

        return taskMapper.toDto(taskRepository.save(existingTask));
    }

    public void deleteTask(Long id) {

        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found with ID: " + id);
        }
        taskRepository.deleteById(id);
    }

    public Page<TaskResponseDto> getAllTasks(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Task> tasks = taskRepository.findAll(pageable);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found.");
        }
        return tasks.map(taskMapper::toDto);
    }

    public Page<TaskResponseDto> getTasksByUser(Long userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Task> tasks = taskRepository.findByUsersId(userId, pageable);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found for the user with ID: " + userId);
        }
        return tasks.map(taskMapper::toDto);
    }

    public Page<TaskResponseDto> getTasksByTitle(String title, Long userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        if (userId != null && !userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }

        Page<Task> tasks;

            tasks = taskRepository.findByTitleContainingIgnoreCaseAndUsersId(title, userId, pageable);

        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found with the title: " + title);
        }
        return tasks.map(taskMapper::toDto);
    }

    public Page<TaskResponseDto> getTasksByStatus(Status status, Long userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        if (userId != null && !userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }

        Page<Task> tasks;

            tasks = taskRepository.findByStatusAndUsersId(status, userId, pageable);

        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found with the status: " + status);
        }
        return tasks.map(taskMapper::toDto);
    }

    public Page<TaskResponseDto> getTasksByDueDate(LocalDate dueDate, Long userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        if (userId != null && !userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }

        Page<Task> tasks;

        tasks = taskRepository.findByDueDateAndUsersId(dueDate, userId, pageable);


        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found with the due date: " + dueDate);
        }
        return tasks.map(taskMapper::toDto);
    }

    public Page<TaskResponseDto> getTasksByDescription(String description, Long userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        if (userId != null && !userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }

        Page<Task> tasks;

        tasks = taskRepository.findByDescriptionContainingIgnoreCaseAndUsersId(description, userId, pageable);


        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found with the description: " + description);
        }
        return tasks.map(taskMapper::toDto);
    }

    public TaskResponseDto markTaskAsDone(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));

        task.setStatus(Status.DONE);

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDto(updatedTask);
    }
}
