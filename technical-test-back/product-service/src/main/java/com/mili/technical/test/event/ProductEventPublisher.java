package com.mili.technical.test.event;

import com.mili.technical.test.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key.created}")
    private String createdRoutingKey;

    @Value("${app.rabbitmq.routing-key.updated}")
    private String updatedRoutingKey;

    @Value("${app.rabbitmq.routing-key.deleted}")
    private String deletedRoutingKey;

    public void publishProductCreated(Product product) {
        ProductEvent event = new ProductEvent("PRODUCT_CREATED", product);
        rabbitTemplate.convertAndSend(exchange, createdRoutingKey, event);
    }

    public void publishProductUpdated(Product product) {
        ProductEvent event = new ProductEvent("PRODUCT_UPDATED", product);
        rabbitTemplate.convertAndSend(exchange, updatedRoutingKey, event);
    }

    public void publishProductDeleted(Product product) {
        ProductEvent event = new ProductEvent("PRODUCT_DELETED", product);
        rabbitTemplate.convertAndSend(exchange, deletedRoutingKey, event);
    }
}
