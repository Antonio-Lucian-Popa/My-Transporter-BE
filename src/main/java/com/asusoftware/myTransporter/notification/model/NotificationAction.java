package com.asusoftware.myTransporter.notification.model;

import com.asusoftware.myTransporter.user.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "NotificationActions")
@Table(name = "notification_actions")
public class NotificationAction {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    @Column(name = "is_new", nullable = false)
    private boolean isNew;

    // Molte notificationAction puo avere una Notification
    @ManyToOne
    @JoinColumn(name="notification_id", nullable=false)
    private Notification notification;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
