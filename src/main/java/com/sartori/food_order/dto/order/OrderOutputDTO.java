package com.sartori.food_order.dto.order;

import com.sartori.food_order.dto.orderitem.OrderItemOutputDTO;
import com.sartori.food_order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderOutputDTO {

    private Long id;
    private Long clientId;
    private String clientName;
    private LocalDateTime date;
    private OrderStatus status;
    private List<OrderItemOutputDTO> items;
}