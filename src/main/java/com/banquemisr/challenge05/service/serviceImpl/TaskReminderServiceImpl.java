package com.banquemisr.challenge05.service.serviceImpl;

import com.banquemisr.challenge05.entity.Notification;
import com.banquemisr.challenge05.entity.Task;
import com.banquemisr.challenge05.repository.NotificationRepository;
import com.banquemisr.challenge05.repository.TaskRepository;
import com.banquemisr.challenge05.service.TaskReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class TaskReminderServiceImpl implements TaskReminderService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailSenderServiceImpl emailSenderServiceImpl;

    @Autowired
    private NotificationRepository notificationRepository;

    @Scheduled(cron = "0 0 7 * * ?")
    public void sendTaskReminders() {

        LocalDate currentDate = LocalDate.now();

        List<Task> upcomingTasks = taskRepository.findByDueDateBefore(currentDate.plusDays(1));

        for (Task task : upcomingTasks) {

            String emailBody = "Dear "+ task.getUsers().getUsername()+",\n\n"
                    + "This is a reminder that the following task is due soon:\n"
                    + "Title: " + task.getTitle() + "\n"
                    + "Description: " + task.getDescription() + "\n"
                    + "Due Date: " + task.getDueDate() + "\n\n"
                    + "Please make sure to complete it on time.\n\n"
                    + "Best regards,\nYour Task Reminder Service";

            emailSenderServiceImpl.sendEmail(task.getUsers().getEmail(), "Upcoming Task Reminder", emailBody);

            Notification notification = new Notification();
            notification.setEmail(task.getUsers().getEmail());
            notification.setSubject("Upcoming Task Reminder");
            notification.setBody(emailBody);
            notification.setSentAt(LocalDateTime.now());
            notification.setUserId(task.getUsers().getId());
            notification.setTaskId(task.getId());

            notificationRepository.save(notification);
        }
    }
}
