package com.example.trendyolBackendCase.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Cart {

    public static final int MAX_DISTINCT_ITEM_COUNT = 10;
    public static final int MAX_TOTAL_ITEM_COUNT_OF_CART = 30;
    public static final double MAX_TOTAL_AMOUNT_OF_CART = 500000.0;
    public static final int MIN_ITEM_QUANTITY = 1;

}
