package com.sartori.food_order.dto.orderitem;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO used to represent an order item on creation")
public class OrderItemInputDTO {

    @NotNull(message = "{orderitem.productid.notnull}")
    @Schema(description = "Product ID", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long productId;

    @NotNull(message = "{orderitem.quantity.notnull}")
    @Min(value = 1, message = "{orderitem.quantity.min}")
    @Schema(description = "Quantity of product in the order", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantity;
}
