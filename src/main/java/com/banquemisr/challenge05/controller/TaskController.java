package com.banquemisr.challenge05.controller;


import com.banquemisr.challenge05.model.dto.TaskRequestDto;
import com.banquemisr.challenge05.model.dto.TaskResponseDto;
import com.banquemisr.challenge05.model.enums.Status;
import com.banquemisr.challenge05.service.serviceImpl.TaskServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskRequestDto taskRequestDto) {
        return ResponseEntity.ok(taskServiceImpl.createTask(taskRequestDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @GetMapping
    public Page<TaskResponseDto> getAllTasks(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return taskServiceImpl.getAllTasks(pageNo, pageSize);
    }

    @GetMapping("/user/{userId}")
    public Page<TaskResponseDto> getTasksByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return taskServiceImpl.getTasksByUser(userId, pageNo, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDto taskRequestDto) {
        return ResponseEntity.ok(taskServiceImpl.updateTask(id, taskRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskServiceImpl.deleteTask(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/title")
    public Page<TaskResponseDto> getTasksByTitle(
            @RequestParam String title,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return taskServiceImpl.getTasksByTitle(title, userId, pageNo, pageSize);
    }

    @GetMapping("/status")
    public Page<TaskResponseDto> getTasksByStatus(
            @RequestParam Status status,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return taskServiceImpl.getTasksByStatus(status, userId, pageNo, pageSize);
    }

    @GetMapping("/dueDate")
    public Page<TaskResponseDto> getTasksByDueDate(
            @RequestParam LocalDate dueDate,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return taskServiceImpl.getTasksByDueDate(dueDate, userId, pageNo, pageSize);
    }

    @GetMapping("/description")
    public Page<TaskResponseDto> getTasksByDescription(
            @RequestParam String description,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return taskServiceImpl.getTasksByDescription(description, userId, pageNo, pageSize);
    }

    @PutMapping("/{id}/mark-done")
    public ResponseEntity<TaskResponseDto> markTaskAsDone(@PathVariable Long id) {
        return  ResponseEntity.ok(taskServiceImpl.markTaskAsDone(id));
    }

}
