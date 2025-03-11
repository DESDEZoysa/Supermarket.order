package com.eranga.supermarket.order.controller;

import com.eranga.supermarket.order.model.dto.OrderDto;
import com.eranga.supermarket.order.model.enums.OrderFailReasonEnum;
import com.eranga.supermarket.order.model.enums.OrderStatusEnum;
import com.eranga.supermarket.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderDto createOrder(@Valid @RequestBody OrderDto orderDto){
        return orderService.createOrder(orderDto);
    }

    @PutMapping("/{orderId}/{status}/{failReason}")
    public void updateOrderStatus(@PathVariable Long orderId, @PathVariable OrderStatusEnum orderStatusEnum, @PathVariable OrderFailReasonEnum failReason){
        orderService.updateOrderStatus(orderId,orderStatusEnum,failReason);
    }
}
