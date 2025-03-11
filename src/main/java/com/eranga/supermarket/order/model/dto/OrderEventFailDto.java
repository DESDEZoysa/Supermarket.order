package com.eranga.supermarket.order.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderEventFailDto {

//    private OrderFailTypeEnum FailType;
    private OrderDto orderDto;
}
