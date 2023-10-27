package com.example.trendyolBackendCase.entity;

public class DigitalItem extends Item{

    public static final int DIGITAL_ITEM_CATEGORY_ID = 7889;
    public static final int MAX_DIGITAL_ITEM_QUANTITY = 5;


    public DigitalItem(int id, double price, int sellerId, int quantity)
    {
        super(id,price,sellerId,DIGITAL_ITEM_CATEGORY_ID, quantity);
    }




}
