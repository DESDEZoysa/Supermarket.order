package com.eranga.supermarket.order.repository;

import com.eranga.supermarket.order.model.entity.OrderEntity;
import com.eranga.supermarket.order.model.enums.OrderFailReasonEnum;
import com.eranga.supermarket.order.model.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    @Modifying
    @Query("UPDATE OrderEntity o SET o.status = :orderStatusEnum, o.failReason = :failReason WHERE o.id = :orderId")
    void updateOrderStatus(@Param("orderId") Long orderId, @Param("orderStatusEnum") String orderStatusEnum, @Param("failReason") String failReason);
}
