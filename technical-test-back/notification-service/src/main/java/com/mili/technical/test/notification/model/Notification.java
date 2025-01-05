package com.mili.technical.test.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private String message;
    private String type;
    private LocalDateTime timestamp;

    public static Notification of(String message, String type) {
        return new Notification(message, type, LocalDateTime.now());
    }
}
