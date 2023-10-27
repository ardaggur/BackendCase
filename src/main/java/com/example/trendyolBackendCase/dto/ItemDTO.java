package com.example.trendyolBackendCase.dto;

import com.example.trendyolBackendCase.entity.Item;
import com.example.trendyolBackendCase.entity.VasItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {

    private Integer itemId;
    private Integer categoryId;
    private Integer sellerId;
    private Double price;
    private Integer quantity;
    private List<Item> vasItems;

    public ItemDTO(Integer itemId, Integer categoryId, Integer sellerId, Double price, Integer quantity, List<Item> vasItems) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.sellerId = sellerId;
        this.price = price;
        this.quantity = quantity;
        this.vasItems = vasItems;
    }

}
