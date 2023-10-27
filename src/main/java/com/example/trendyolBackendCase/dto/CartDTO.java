package com.example.trendyolBackendCase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO {

    private List<ItemDTO> itemDTO;
    private Double totalPrice;
    private Integer appliedPromotionId;
    private Double totalDiscount;

    public CartDTO(List<ItemDTO> itemDTO, Double totalPrice, Integer appliedPromotionId, Double totalDiscount) {
        this.itemDTO = itemDTO;
        this.totalPrice = totalPrice;
        this.appliedPromotionId = appliedPromotionId;
        this.totalDiscount = totalDiscount;
    }

}
