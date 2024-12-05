package com.banquemisr.challenge05.service.serviceImpl;

import com.banquemisr.challenge05.entity.Notification;
import com.banquemisr.challenge05.entity.Users;
import com.banquemisr.challenge05.exception.UserNotFoundException;
import com.banquemisr.challenge05.repository.NotificationRepository;
import com.banquemisr.challenge05.repository.UserRepository;
import com.banquemisr.challenge05.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Notification> getNotificationForUser(Long userId){
        Users users = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        return notificationRepository.findByUserId(userId);
    }

}
