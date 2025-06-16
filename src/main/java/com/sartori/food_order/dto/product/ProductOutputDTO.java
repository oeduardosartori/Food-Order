package com.sartori.food_order.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOutputDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
