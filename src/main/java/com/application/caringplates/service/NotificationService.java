package com.application.caringplates.service;

import com.application.caringplates.models.Notification;
import com.application.caringplates.repository.NotificationRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<Notification> fetchNotificationsById(Long userId){
        return notificationRepository.findNotificationsByUserID(userId);
    }

    public Integer getRewardPoints(long userId){
        List<Notification> notificationList = notificationRepository.findAllByUserID(userId);
        return notificationList.size()*10;
    }
}
