package com.banquemisr.challenge05.repository;

import com.banquemisr.challenge05.entity.Task;
import com.banquemisr.challenge05.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    Page<Task> findAll(Pageable pageable);

    Page<Task> findByTitleContainingIgnoreCaseAndUsersId(String title, Long userId, Pageable pageable);

    Page<Task> findByStatusAndUsersId(Status status, Long userId, Pageable pageable);

    Page<Task> findByDueDateAndUsersId(LocalDate dueDate, Long userId, Pageable pageable);

    Page<Task> findByDescriptionContainingIgnoreCaseAndUsersId(String description, Long userId, Pageable pageable);
    List<Task> findByDueDateBefore(LocalDate dueDate);

    Page<Task> findByUsersId(Long userId, Pageable pageable);



}
