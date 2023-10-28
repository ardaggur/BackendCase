package com.example.trendyolBackendCase.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {

    public static final int MAX_ITEM_COUNT = 10;

    @Id
    @Column
    private int id;
    @Column
    private double price;
    @Column
    private int sellerId;
    @Column
    private int categoryId;
    @Column
    private int quantity;

    public Item(int id, double price, int sellerId, int categoryId, int quantity){
        this.id = id;
        this.price = price;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.quantity = quantity;
    }

    public Item() {

    }
}
