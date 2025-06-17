package com.sartori.food_order.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO used to create a new product")
public class ProductInputDTO {

    @NotBlank(message = "{product.name.notblank}")
    @Schema(description = "Product name", example = "Pizza Calabresa", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Size(max = 255, message = "{product.description.size}")
    @Schema(description = "Optional product description", example = "Pizza com calabresa e cebola caramelizada")
    private String description;

    @NotNull(message = "{product.price.notnull}")
    @Positive(message = "{product.price.positive}")
    @Schema(description = "Product price", example = "49.90", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal price;
}
