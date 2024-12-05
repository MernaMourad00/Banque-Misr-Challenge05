package com.banquemisr.challenge05.controller;

import com.banquemisr.challenge05.entity.Notification;
import com.banquemisr.challenge05.service.serviceImpl.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;

    @GetMapping("/{userId}")
    public List<Notification> getNotificationsForUser(@PathVariable Long userId) {
        return notificationServiceImpl.getNotificationForUser(userId);
    }
}
