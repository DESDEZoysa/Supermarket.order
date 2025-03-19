package com.eranga.supermarket.order.service.impl;

import com.eranga.supermarket.order.model.dto.OrderDto;
import com.eranga.supermarket.order.model.enums.OrderFailReasonEnum;
import com.eranga.supermarket.order.model.enums.OrderStatusEnum;
import com.eranga.supermarket.order.repository.OrderRepository;
import com.eranga.supermarket.order.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, OrderDto> kafkaOrderTemplate;
    private final OrderRepository orderRepository;

    @Value("${spring.kafka.topic.new_order}")
    private String newOrderTopic;
    private static final Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);

    @Override
    public void sendOrderEvent(OrderDto orderDto) {
        try {
            RecordMetadata metadata =kafkaOrderTemplate.send(newOrderTopic, orderDto).get().getRecordMetadata();
            logger.info("Sent new order event = {} to partition = {}", orderDto,metadata.partition());
        }catch (Exception e){
            logger.error("Unable to send new order event = {}", orderDto);
            throw new RuntimeException("New order event sending failed", e);
        }

//        CompletableFuture<SendResult<String, OrderDto>> completableFuture = kafkaOrderTemplate.send(newOrderTopic, orderDto);
//        completableFuture.whenComplete((result, ex) -> {
//            if (ex == null) {
//                logger.info("Sent new order event = {}", orderDto);
//            } else {
//                logger.error("Unable to send new order event = {}", orderDto);
//            }
//        });
    }
}
