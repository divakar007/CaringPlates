package com.application.caringplates.repository;

import com.application.caringplates.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long > {
    public List<Notification> findNotificationsByUserID(Long userID);
    List<Notification> findAllByUserID(Long userId);
}
