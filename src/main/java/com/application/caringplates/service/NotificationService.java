package com.application.caringplates.service;

import com.application.caringplates.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    public void addNotificationForUser(String message, Long userId) {
        notificationRepository.updateNotificationMessageForRestaurantUser(message,userId);
    }

    public void addNotificationForRestaurant(String message, Long userId) {
        notificationRepository.updateNotificationMessageForRestaurantUser(message,userId);
    }

}
