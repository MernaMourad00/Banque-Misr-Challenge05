package com.banquemisr.challenge05.service;

import com.banquemisr.challenge05.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    List<Notification> getNotificationForUser(Long userId);

}
