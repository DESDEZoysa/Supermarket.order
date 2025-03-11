package com.eranga.supermarket.order.service;

import com.eranga.supermarket.order.model.dto.OrderDto;
import com.eranga.supermarket.order.model.enums.OrderFailReasonEnum;
import com.eranga.supermarket.order.model.enums.OrderStatusEnum;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);
    void updateOrderStatus(Long orderId, OrderStatusEnum orderStatusEnum, OrderFailReasonEnum failReason);
}
