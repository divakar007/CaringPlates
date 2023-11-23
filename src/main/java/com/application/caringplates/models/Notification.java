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

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "postid", referencedColumnName = "postID")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "restaurnatid", referencedColumnName = "restId")
    private Restaurant restaurant;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;
}
