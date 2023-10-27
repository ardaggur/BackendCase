package com.example.trendyolBackendCase.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class DefaultItem extends Item{

    public static final int MAX_VAS_ITEM_COUNT = 3;

    public DefaultItem(int id, double price, int sellerId, int categoryId,int quantity){
        super(id, price, sellerId, categoryId, quantity);
    }



}
