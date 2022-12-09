package com.javatechie.os.api.common;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
    private Integer id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
