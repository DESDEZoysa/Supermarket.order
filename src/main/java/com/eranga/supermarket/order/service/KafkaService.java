package com.eranga.supermarket.order.service;

import com.eranga.supermarket.order.model.dto.OrderDto;

public interface KafkaService {

    void sendOrderEvent(OrderDto orderDto);
}
