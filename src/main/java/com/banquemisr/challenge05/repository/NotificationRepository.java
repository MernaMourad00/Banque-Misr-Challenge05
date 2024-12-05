package com.banquemisr.challenge05.repository;

import com.banquemisr.challenge05.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findByUserId(Long userId);
    List<Notification> findByTaskId(Long taskId);
}