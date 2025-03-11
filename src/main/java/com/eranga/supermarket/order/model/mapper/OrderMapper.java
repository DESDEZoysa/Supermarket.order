package com.eranga.supermarket.order.model.mapper;

import com.eranga.supermarket.order.model.dto.OrderDto;
import com.eranga.supermarket.order.model.dto.OrderItemDto;
import com.eranga.supermarket.order.model.entity.OrderEntity;
import com.eranga.supermarket.order.model.entity.OrderItemEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderEntity orderDtoToEntity(OrderDto orderDto){
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderDto,orderEntity);
        orderEntity.setOrderItemEntityList(orderItemDtoToEntityList(orderDto.getOrderItemDtoList(),orderEntity));
        return orderEntity;
    }

    public OrderDto orderEntityToDto(OrderEntity orderEntity){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderEntity,orderDto);
        orderDto.setOrderItemDtoList(orderItemEntityToDtoList(orderEntity.getOrderItemEntityList()));
        return orderDto;
    }

    public OrderItemEntity orderItemDtoToEntity(OrderItemDto orderItemDto, OrderEntity orderEntity){
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        BeanUtils.copyProperties(orderItemDto,orderItemEntity);
        orderItemEntity.setOrderEntity(orderEntity);
        return orderItemEntity;
    }

    public OrderItemDto orderItemEntityToDto(OrderItemEntity orderItemEntity){
        OrderItemDto orderItemDto = new OrderItemDto();
        BeanUtils.copyProperties(orderItemEntity,orderItemDto);
        orderItemDto.setOrderId(orderItemEntity.getOrderEntity().getId());
        return orderItemDto;
    }

    public List<OrderItemEntity> orderItemDtoToEntityList(List<OrderItemDto> orderItemDtoList, OrderEntity orderEntity){
        return orderItemDtoList.stream().map(orderItemDto ->{
                return orderItemDtoToEntity(orderItemDto,orderEntity);
        }).collect(Collectors.toList());
    }

    public List<OrderItemDto> orderItemEntityToDtoList(List<OrderItemEntity> orderItemEntityList){
        return orderItemEntityList.stream().map(this::orderItemEntityToDto).collect(Collectors.toList());
    }
}
