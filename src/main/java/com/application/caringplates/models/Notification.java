package com.application.caringplates.models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificationID")
    private Long notificationID;

    @Column(name = "userID")
    private Long userID;

    @Column(name = "postID")
    private Long postID;


    @Column(name = "message", columnDefinition = "TEXT")
    private String message;
}
