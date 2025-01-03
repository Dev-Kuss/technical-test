package com.mili.technical.test.notification.service;

import com.mili.technical.test.notification.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductEventListener {

    private final SimpMessagingTemplate messagingTemplate;

    @Value("${app.websocket.topic}")
    private String topic;

    @RabbitListener(queues = "${app.rabbitmq.queue.product-events}")
    public void handleProductEvent(String event) {
        // Parse the event and create a notification
        Notification notification = createNotificationFromEvent(event);
        
        // Send to all connected WebSocket clients
        messagingTemplate.convertAndSend(topic, notification);
    }

    private Notification createNotificationFromEvent(String event) {
        // Simple event parsing logic - you might want to make this more sophisticated
        String type = event.contains("CREATED") ? "SUCCESS" :
                     event.contains("UPDATED") ? "INFO" :
                     event.contains("DELETED") ? "WARNING" : "INFO";

        String message = switch (type) {
            case "SUCCESS" -> "New product has been created";
            case "INFO" -> "Product has been updated";
            case "WARNING" -> "Product has been deleted";
            default -> "Product event received";
        };

        return Notification.of(message, type);
    }
}
