package com.application.caringplates.controller;

import com.application.caringplates.models.Notification;
import com.application.caringplates.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/fetch-notifications/{userId}")
    public ResponseEntity<List<Notification>> fetch(@PathVariable Long userId){
        try {
            List<Notification> notifications = notificationService.fetchNotificationsById(userId);
            return ResponseEntity.ok().body(notifications);
        }
        catch (Exception ignored){
            return ResponseEntity.ok().body(null);
        }
    }
}
