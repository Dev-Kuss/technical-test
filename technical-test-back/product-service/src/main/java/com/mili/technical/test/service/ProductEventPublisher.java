package com.mili.technical.test.service;

import com.mili.technical.test.event.ProductEvent;
import com.mili.technical.test.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key.product-events}")
    private String routingKey;

    public void publishProductCreated(Product product) {
        ProductEvent event = ProductEvent.of("PRODUCT_CREATED", product);
        rabbitTemplate.convertAndSend(exchange, "product.events.created", event);
    }

    public void publishProductUpdated(Product product) {
        ProductEvent event = ProductEvent.of("PRODUCT_UPDATED", product);
        rabbitTemplate.convertAndSend(exchange, "product.events.updated", event);
    }

    public void publishProductDeleted(Product product) {
        ProductEvent event = ProductEvent.of("PRODUCT_DELETED", product);
        rabbitTemplate.convertAndSend(exchange, "product.events.deleted", event);
    }
}
