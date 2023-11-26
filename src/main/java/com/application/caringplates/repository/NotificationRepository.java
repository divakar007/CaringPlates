package com.application.caringplates.repository;

import com.application.caringplates.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends JpaRepository<Notification, Long > {

    @Modifying
    @Query(value = "UPDATE notification SET message = :message WHERE userID = :userId", nativeQuery = true)
    void updateNotificationMessageForUser(@Param("message") String message, @Param("userId") Long userId);

    @Modifying
    @Query(value = "UPDATE notification SET message = :message WHERE userID = :userId", nativeQuery = true)
    void updateNotificationMessageForRestaurantUser(@Param("message") String message, @Param("userId") Long userId);

}
