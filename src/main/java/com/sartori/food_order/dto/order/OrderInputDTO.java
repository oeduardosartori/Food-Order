package com.sartori.food_order.dto.order;

import com.sartori.food_order.dto.orderitem.OrderItemInputDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO used to create a new order")
public class OrderInputDTO {

    @NotNull(message = "{order.clientid.notnull}")
    @Schema(description = "ID of the customer who placed the order", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long clientId;

    @Size(min = 1, message = "{order.items.size}")
    @NotNull(message = "{order.items.notnull}")
    @Schema(description = "Order Item List", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<@Valid OrderItemInputDTO> items;
}