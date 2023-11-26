package com.application.caringplates.repository;

import com.application.caringplates.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long > {

}
