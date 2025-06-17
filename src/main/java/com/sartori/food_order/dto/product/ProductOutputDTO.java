package com.sartori.food_order.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOutputDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
