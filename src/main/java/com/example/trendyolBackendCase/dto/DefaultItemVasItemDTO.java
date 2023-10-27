package com.example.trendyolBackendCase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DefaultItemVasItemDTO {

    private Integer itemId;
    private Integer vasItemId;
    private Integer categoryId;
    private Integer sellerId;
    private Double price;
    private Integer quantity;

    public DefaultItemVasItemDTO(Integer itemId, Integer vasItemId, Integer categoryId, Integer sellerId, Double price, Integer quantity) {
        this.itemId = itemId;
        this.vasItemId = vasItemId;
        this.categoryId = categoryId;
        this.sellerId = sellerId;
        this.price = price;
        this.quantity = quantity;
    }

}
