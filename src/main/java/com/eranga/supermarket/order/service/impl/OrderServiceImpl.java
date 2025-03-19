package com.eranga.supermarket.order.service.impl;

import com.eranga.supermarket.order.model.dto.OrderDto;
import com.eranga.supermarket.order.model.entity.OrderEntity;
import com.eranga.supermarket.order.model.enums.OrderFailReasonEnum;
import com.eranga.supermarket.order.model.enums.OrderStatusEnum;
import com.eranga.supermarket.order.model.mapper.OrderMapper;
import com.eranga.supermarket.order.repository.OrderRepository;
import com.eranga.supermarket.order.service.KafkaService;
import com.eranga.supermarket.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final KafkaService kafkaService;

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        OrderEntity orderEntity =orderRepository.save(orderMapper.orderDtoToEntity(orderDto));
        orderDto = orderMapper.orderEntityToDto(orderEntity);
        kafkaService.sendOrderEvent(orderDto);
        return orderDto;
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatusEnum orderStatusEnum, OrderFailReasonEnum failReason) {
        orderRepository.updateOrderStatus(orderId,orderStatusEnum.name(),failReason.name());
    }
}
