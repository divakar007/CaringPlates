package com.application.caringplates.service;

import com.application.caringplates.models.Notification;
import com.application.caringplates.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;


    public void createNotification(Long userId, Long postId, String message) {
        Notification notification = new Notification();
        notification.setUserID(userId);
        notification.setPostID(postId);
        notification.setMessage(message);

        notificationRepository.save(notification);
    }
}
