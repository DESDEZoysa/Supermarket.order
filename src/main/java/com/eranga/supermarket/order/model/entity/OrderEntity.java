package com.eranga.supermarket.order.model.entity;

import com.eranga.supermarket.order.model.enums.OrderFailReasonEnum;
import com.eranga.supermarket.order.model.enums.OrderStatusEnum;
import com.eranga.supermarket.order.model.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
    private Long id;
    @Version
    private Long version;
    private Integer customerId;
    @Column(name = "order_date", nullable = false)
    private String orderDate;
    @Column(name = "status", nullable = false)
    private OrderStatusEnum status;
    private OrderFailReasonEnum failReason;
    @Column(name = "method", nullable = false)
    private PaymentMethodEnum method;
    @Column(name = "amount", nullable = false)
    private Double amount;
    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItemEntityList;

}
