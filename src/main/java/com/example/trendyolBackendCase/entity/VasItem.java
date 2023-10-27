package com.example.trendyolBackendCase.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
public class VasItem extends Item{

    public static final int VAS_ITEM_CATEGORY_ID = 3242;
    public static final int VAS_ITEM_SELLER_ID = 5003;

    public VasItem(int id, double price, int quantity){
        super(id, price, VAS_ITEM_SELLER_ID, VAS_ITEM_CATEGORY_ID, quantity);
    }

}
